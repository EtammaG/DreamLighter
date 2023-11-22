package com.etammag.dreamlighter.controller.volunteer;

import com.etammag.dreamlighter.service.kid.KidInfoService;
import com.etammag.icommon.entity.Result;
import com.etammag.dreamlighter.entity.kid.KidRecDto;
import com.etammag.dreamlighter.entity.kid.db.Kid;
import com.etammag.dreamlighter.service.volunteer.VolunKidService;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/volunteer/kid")
@PreAuthorize("hasAuthority('VOLUNTEER')")
@Api(tags = "志愿者端孩子信息接口")
public class VolunKidController {

    private final KidInfoService kidInfoService;

    private final VolunKidService volunKidService;

    @Autowired
    public VolunKidController(KidInfoService kidInfoService, VolunKidService volunKidService) {
        this.kidInfoService = kidInfoService;
        this.volunKidService = volunKidService;
    }

    @GetMapping(value = "/random/recent")
    @ApiOperation(value = "主页面孩子近况")
    public Result<List<KidRecDto>> getRandom() {
        return Result.success(kidInfoService.getRandomRecs());
    }

    @GetMapping(value = "/all")
    @ApiOperation(value = "孩子信息列表")
    public Result<IPageInfo<Kid>> getAll(@RequestBody IPage iPage) {
        return Result.success(volunKidService.pageAll(iPage));
    }


}