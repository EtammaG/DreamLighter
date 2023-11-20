package com.etammag.dreamlighter.controller.kid;

import com.etammag.dreamlighter.common.entity.Result;
import com.etammag.dreamlighter.entity.kid.db.Reply;
import com.etammag.dreamlighter.service.kid.ReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/kid/reply")
@PreAuthorize("hasAuthority('KID')")
@Api(tags = "孩子端孩子任务提交相关接口")
public class KidReplyController {

    private final ReplyService replyService;

    @Autowired
    public KidReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @GetMapping("/{missionId}")
    @ApiOperation("获取任务的答题情况")
    public Result<Reply> getByMission(@PathVariable @ApiParam(value = "任务ID") String missionId) {
        return Result.success(replyService.getByKidIdAndMissionId(missionId));
    }

    @PostMapping
    @ApiOperation("答题")
    public Result<Object> post(@RequestBody Map<String, String> map) {
        replyService.add(
                Long.valueOf(map.get("missionId")),
                map.get("replyMedia"));
        return Result.success();
    }


}
