package com.etammag.dreamlighter.controller.donor;

import com.etammag.icommon.entity.Result;
import com.etammag.dreamlighter.entity.common.DesDto;
import com.etammag.dreamlighter.service.common.HotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/donor/hot")
@PreAuthorize("hasAuthority('DONOR')")
@Api(tags = "捐赠者端推送热门消息相关接口")
public class DonorHotController {

    private final HotService hotService;

    @Autowired
    public DonorHotController(HotService hotService) {
        this.hotService = hotService;
    }

    @GetMapping("/des")
    @ApiOperation("获得系统的宣传信息")
    public Result<DesDto> get() {
        return Result.success(hotService.getSysDes());
    }
}
