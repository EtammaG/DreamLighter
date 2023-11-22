package com.etammag.dreamlighter.controller.kid;

import com.etammag.icommon.context.BaseInfoContext;
import com.etammag.icommon.entity.Result;
import com.etammag.dreamlighter.entity.kid.KidMalDto;
import com.etammag.dreamlighter.entity.kid.KidMeeDto;
import com.etammag.dreamlighter.entity.kid.KidMisDto;
import com.etammag.dreamlighter.entity.kid.KidRecDto;
import com.etammag.dreamlighter.service.kid.KidInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kid/info")
@PreAuthorize("hasAuthority('KID')")
@Api(tags = "孩子端孩子信息相关接口")
public class KidInfoController {

    private final KidInfoService kidInfoService;

    @Autowired
    public KidInfoController(KidInfoService kidInfoService) {
        this.kidInfoService = kidInfoService;
    }

    @GetMapping("mission")
    @ApiOperation("获取孩子任务相关的信息")
    public Result<KidMisDto> mission() {
        return Result.success(kidInfoService.getMis(BaseInfoContext.get().getId()));
    }

    @GetMapping("mall")
    @ApiOperation("获取孩子商城相关的信息")
    public Result<KidMalDto> mall() {
        return Result.success(kidInfoService.getMal(BaseInfoContext.get().getId()));
    }

    @GetMapping("me")
    @ApiOperation("获取孩子我的相关的信息")
    public Result<KidMeeDto> me() {
        return Result.success(kidInfoService.getMee(BaseInfoContext.get().getId()));
    }

    @GetMapping("recent")
    @ApiOperation("获取孩子最近信息")
    public Result<KidRecDto> recent() {
        return Result.success(kidInfoService.getRec(BaseInfoContext.get().getId()));
    }

}
