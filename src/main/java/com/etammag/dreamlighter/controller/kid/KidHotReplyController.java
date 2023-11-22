package com.etammag.dreamlighter.controller.kid;

import com.etammag.icommon.context.BaseInfoContext;
import com.etammag.icommon.entity.Result;
import com.etammag.dreamlighter.entity.common.CommentDto;
import com.etammag.dreamlighter.entity.kid.HotReplyDto;
import com.etammag.dreamlighter.service.kid.HotReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kid/hot/reply")
@PreAuthorize("hasAuthority('KID')")
@Api(tags = "孩子端推送热门提交相关接口")
public class KidHotReplyController {

    private final HotReplyService hotReplyService;

    @Autowired
    public KidHotReplyController(HotReplyService hotReplyService) {
        this.hotReplyService = hotReplyService;
    }

    @GetMapping
    @ApiOperation("获取热门提交列表")
    public Result<List<HotReplyDto>> get() {
        return Result.success(hotReplyService.getAll(BaseInfoContext.get().getId()));
    }

    @PostMapping("/like")
    @ApiOperation("对热门提交点赞")
    public Result<Object> like(@RequestBody Map<String, String> map) {
        hotReplyService.like(BaseInfoContext.get().getId(), Long.parseLong(map.get("hotId")));
        return Result.success();
    }

    @PostMapping("/unlike")
    @ApiOperation("对热门提交取消点赞")
    public Result<Object> unlike(@RequestBody Map<String, String> map) {
        String hotId = map.get("hotId");
        hotReplyService.unlike(BaseInfoContext.get().getId(), Long.parseLong(hotId));
        return Result.success();
    }

    @GetMapping("/{hotId}/comment")
    @ApiOperation("获取热门提交的评论列表")
    public Result<List<CommentDto>> getComment(@PathVariable @ApiParam(value = "热门提交的ID") String hotId) {
        return Result.success(hotReplyService.getComment(hotId));
    }

    @PostMapping("/comment")
    @ApiOperation("对热门提交评论")
    public Result<Object> addComment(@RequestBody Map<String, String> map) {
        String hotId = map.get("hotId");
        String comment = map.get("content");
        hotReplyService.addComment(BaseInfoContext.get().getId(), Long.parseLong(hotId), comment);
        return Result.success();
    }


}
