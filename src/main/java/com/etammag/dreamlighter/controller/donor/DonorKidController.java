package com.etammag.dreamlighter.controller.donor;

import com.etammag.dreamlighter.common.entity.Result;
import com.etammag.dreamlighter.entity.common.ITypePage;
import com.etammag.dreamlighter.entity.kid.KidDto;
import com.etammag.dreamlighter.entity.kid.KidRecDto;
import com.etammag.dreamlighter.entity.kid.KidSimDto;
import com.etammag.dreamlighter.entity.kid.db.Type;
import com.etammag.dreamlighter.service.kid.KidInfoService;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donor/kid")
@PreAuthorize("hasAuthority('DONOR')")
@Api(tags = "捐赠者端获取孩子信息相关接口")
public class DonorKidController {

    private final KidInfoService kidInfoService;

    @Autowired
    public DonorKidController(KidInfoService kidInfoService) {
        this.kidInfoService = kidInfoService;
    }

    @GetMapping("/recent")
    @ApiOperation("获取某些孩子近况列表")
    public Result<List<KidRecDto>> recent() {
        return Result.success(kidInfoService.getRandomRecs());
    }

    @GetMapping("/type")
    @ApiOperation("获取孩子类别列表")
    public Result<List<Type>> type() {
        return Result.success(kidInfoService.getAllType());
    }

    @PostMapping("/search")
    @ApiOperation("搜索孩子简单信息")
    public Result<IPageInfo<KidSimDto>> searchByType(@RequestBody ITypePage iTypePage) {
        return Result.success(kidInfoService.getSimByType(iTypePage, iTypePage.getTypeId()));
    }

    @PostMapping("/list")
    @ApiOperation("获取孩子简单信息")
    public Result<IPageInfo<KidSimDto>> list(@RequestBody IPage iPage) {
        return Result.success(kidInfoService.getSim(iPage));
    }

    @GetMapping("/{id}")
    @ApiOperation("获取指定孩子的详细信息")
    public Result<KidDto> getById(@PathVariable @ApiParam("孩子ID") String id) {
        return Result.success(kidInfoService.getById(Long.parseLong(id)));
    }

}
