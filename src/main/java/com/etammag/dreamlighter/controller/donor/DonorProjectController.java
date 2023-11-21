package com.etammag.dreamlighter.controller.donor;

import com.etammag.icommon.entity.Result;
import com.etammag.dreamlighter.entity.common.ITypePage;
import com.etammag.dreamlighter.entity.donor.ProjectSimDto;
import com.etammag.dreamlighter.entity.donor.db.Project;
import com.etammag.dreamlighter.entity.donor.db.ProjectType;
import com.etammag.dreamlighter.service.donor.DonorProjectService;
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
@RequestMapping("/donor/project")
@PreAuthorize("hasAuthority('DONOR')")
@Api(tags = "捐赠者端获取项目信息相关接口")
public class DonorProjectController {

    private final DonorProjectService donorProjectService;

    @Autowired
    public DonorProjectController(DonorProjectService donorProjectService) {
        this.donorProjectService = donorProjectService;
    }

    @GetMapping("/type")
    @ApiOperation("获取项目类别列表")
    public Result<List<ProjectType>> type() {
        return Result.success(donorProjectService.getAllType());
    }

    @PostMapping("/search")
    @ApiOperation("搜索项目简单信息")
    public Result<IPageInfo<ProjectSimDto>> searchByType(@RequestBody ITypePage iTypePage) {
        return Result.success(donorProjectService.getSimByType(iTypePage, iTypePage.getTypeId()));
    }

    @PostMapping("/list")
    @ApiOperation("搜索项目简单信息")
    public Result<IPageInfo<ProjectSimDto>> list(@RequestBody IPage iPage) {
        return Result.success(donorProjectService.getSim(iPage));
    }

    @GetMapping("/{id}")
    @ApiOperation("获取指定项目的详细信息")
    public Result<Project> getById(@PathVariable @ApiParam("项目ID") String id) {
        return Result.success(donorProjectService.getById(id));
    }


}
