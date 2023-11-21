package com.etammag.dreamlighter.controller.volunteer;

import com.etammag.icommon.entity.Result;
import com.etammag.dreamlighter.entity.kid.KidVieDto;
import com.etammag.dreamlighter.entity.mission.MissionDto;
import com.etammag.dreamlighter.entity.mission.MissionVolViewDto;
import com.etammag.dreamlighter.service.volunteer.VolunteerMissionService;
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
public class VolunteerMissionController {

    private final VolunteerMissionService volunteerMissionService;

    @Autowired
    public VolunteerMissionController(VolunteerMissionService volunteerMissionService) {
        this.volunteerMissionService = volunteerMissionService;
    }

    @GetMapping(value = "/random")
    @ApiOperation(value = "志愿者主页面滑动的我的任务")
    public Result<MissionVolViewDto> getMission() {
        return Result.success(volunteerMissionService.getRandomMission());
    }

    @GetMapping(value = "/totalMissionCount")
    @ApiOperation(value = "志愿者总任务，和未完成的总任务数")
    public Result<Map<String, Long>> getMissionTotal() {
        return Result.success(volunteerMissionService.getMissionTatal());
    }

    @PostMapping(value = "/list")
    @ApiOperation(value = "志愿者端任务列表")
    public Result<IPageInfo<MissionVolViewDto>> getAllMission(@RequestBody IPage iPage) {
        return Result.success(volunteerMissionService.getAllMission(iPage));
    }

    @PostMapping(value = "/allkid/detail")
    @ApiOperation("单独任务孩子的完成情况")
    public Result<IPageInfo<KidVieDto>> getMissionAllDetail(@RequestBody Map<String, String> map) {
        IPage iPage = new IPage(
                Integer.parseInt(map.get("pageNum")),
                Integer.parseInt(map.get("pageSize")),
                map.get("orderBy"));
        return Result.success(volunteerMissionService.getKidInfo(iPage, Long.valueOf(map.get("missionId"))));
    }

    @GetMapping(value = "/detail")
    @ApiOperation("该任务的详细信息")
    public Result<MissionDto> getMissionDetail(@ApiParam(name = "id", value = "任务id") @RequestParam String id) {
        return Result.success(volunteerMissionService.getMissionDetail(Long.parseLong(id)));
    }


    @PostMapping(value = "/inputscore")
    @ApiOperation("给孩子完成的任务打分")
    public void saveScore(@RequestBody Map<String, Object> map) {
        volunteerMissionService.putScore(Long.valueOf((String) map.get("missionId")), Long.valueOf((String) map.get("kidId")), (Integer) map.get("score"));
    }


}