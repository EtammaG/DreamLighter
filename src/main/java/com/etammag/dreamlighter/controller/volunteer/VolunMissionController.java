package com.etammag.dreamlighter.controller.volunteer;

import com.etammag.dreamlighter.entity.kid.db.Reply;
import com.etammag.dreamlighter.entity.volunteer.VolunMisDto;
import com.etammag.dreamlighter.service.volunteer.VolunInfoService;
import com.etammag.icommon.entity.Result;
import com.etammag.dreamlighter.entity.mission.ReplyVieDto;
import com.etammag.dreamlighter.entity.mission.MissionDto;
import com.etammag.dreamlighter.entity.mission.MissionVolViewDto;
import com.etammag.dreamlighter.service.volunteer.VolunMissionService;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/volunteer/mission")
@PreAuthorize("hasAuthority('VOLUNTEER')")
@Api(tags = "志愿者任务相关信息接口")
public class VolunMissionController {

    private final VolunMissionService volunMissionService;
    private final VolunInfoService volunInfoService;

    @Autowired
    public VolunMissionController(VolunMissionService volunMissionService, VolunInfoService volunInfoService) {
        this.volunMissionService = volunMissionService;
        this.volunInfoService = volunInfoService;
    }

    @GetMapping(value = "/des")
    @ApiOperation(value = "志愿者总任务，和未完成的总任务数")
    public Result<VolunMisDto> getMissionTotal() {
        return Result.success(volunInfoService.getVolunMisInfo());
    }

    @PostMapping(value = "/page")
    @ApiOperation(value = "志愿者端任务列表")
    public Result<IPageInfo<MissionVolViewDto>> getAllMission(@RequestBody IPage iPage) {
        return Result.success(volunMissionService.pageAll(iPage));
    }

    @PostMapping(value = "/reply/{missionId}")
    @ApiOperation("单独任务孩子的完成情况")
    public Result<IPageInfo<ReplyVieDto>> getMissionAllDetail(
            @PathVariable String missionId,
            @RequestBody Map<String, String> map) {
        IPage iPage = new IPage(
                Integer.parseInt(map.get("pageNum")),
                Integer.parseInt(map.get("pageSize")),
                map.get("orderBy"));
        return Result.success(volunMissionService.getReplyVie(iPage, Long.valueOf(missionId)));
    }

    @GetMapping(value = "/{missionId}")
    @ApiOperation("该任务的详细信息")
    public Result<MissionDto> getMissionDetail(@ApiParam(name = "id", value = "任务id") @PathVariable String missionId) {
        return Result.success(volunMissionService.getMissionDto(Long.parseLong(missionId)));
    }

    @PostMapping(value = "/reply/score")
    @ApiOperation("给孩子完成的任务打分")
    public Result<Object> score(@RequestBody Reply reply) {
        volunMissionService.score(reply);
        return Result.success();
    }


}