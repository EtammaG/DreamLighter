package com.etammag.dreamlighter.controller.volunteer;

import com.etammag.dreamlighter.entity.volunteer.db.Volunteer;
import com.etammag.dreamlighter.service.volunteer.VolunInfoService;
import com.etammag.icommon.context.BaseInfoContext;
import com.etammag.icommon.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/volunteer")
@PreAuthorize("hasAuthority('VOLUNTEER')")
@Api(tags = "志愿者端相关信息接口")
public class VolunInfoController {

    private final VolunInfoService volunInfoService;

    @Autowired
    public VolunInfoController(VolunInfoService volunInfoService) {
        this.volunInfoService = volunInfoService;
    }

    @GetMapping(value = "/info")
    @ApiOperation(value = "志愿者信息")
    public Result<Volunteer> getVolunInfo() {
        return Result.success(volunInfoService.getVolunteerInfo(BaseInfoContext.get().getId()));
    }

}
