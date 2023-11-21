package com.etammag.dreamlighter.controller.volunteer;

import com.etammag.icommon.entity.Result;
import com.etammag.dreamlighter.entity.kid.KidRecDto;
import com.etammag.dreamlighter.entity.kid.db.Kid;
import com.etammag.dreamlighter.service.volunteer.VolunteerKidService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/volunteer/kid")
@PreAuthorize("hasAuthority('VOLUNTEER')")
@Api(tags = "志愿者端孩子信息接口")
public class VolunteerKidController {

    private final VolunteerKidService volunteerKidService;

    @Autowired
    public VolunteerKidController(VolunteerKidService volunteerKidService) {
        this.volunteerKidService = volunteerKidService;
    }

    @GetMapping(value = "/random/recent")
    @ApiOperation(value = "主页面孩子近况")
    public Result<KidRecDto> getRandom() {
        return Result.success(volunteerKidService.getKidRandomRec());
    }

    @GetMapping(value = "/all")
    @ApiOperation(value = "孩子信息列表")
    public Result<List<Kid>> getAll() {
        return Result.success(volunteerKidService.getAllKidInfo());
    }


}