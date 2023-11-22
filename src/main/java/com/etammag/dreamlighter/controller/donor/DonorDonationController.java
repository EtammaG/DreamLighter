package com.etammag.dreamlighter.controller.donor;

import com.etammag.dreamlighter.entity.donor.DonationStaDto;
import com.etammag.dreamlighter.entity.donor.KidDonationDto;
import com.etammag.dreamlighter.entity.donor.KidThingDto;
import com.etammag.dreamlighter.entity.donor.ProjectDonationDto;
import com.etammag.dreamlighter.service.donor.DonorDonationService;
import com.etammag.icommon.context.BaseInfoContext;
import com.etammag.icommon.entity.Result;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/donor/donation")
@PreAuthorize("hasAuthority('DONOR')")
@Api(tags = "捐赠者端捐助相关接口")
public class DonorDonationController {

    private final DonorDonationService donorDonationService;

    @Autowired
    public DonorDonationController(DonorDonationService donorDonationService) {
        this.donorDonationService = donorDonationService;
    }

    @PostMapping("/money")
    @ApiOperation("捐助善款")
    public Result<Object> money(@RequestBody Map<String, String> map) {
        String kidId = map.get("kidId");
        String amount = map.get("amount");
        donorDonationService.addMoney(BaseInfoContext.get().getId(), Long.parseLong(kidId), Integer.parseInt(amount));
        return Result.success();
    }

    @PostMapping("/thing")
    @ApiOperation("捐助物品")
    public Result<Object> thing(@RequestBody Map<String, String> map) {
        String kidId = map.get("kidId");
        String name = map.get("name");
        donorDonationService.addThing(BaseInfoContext.get().getId(), Long.valueOf(kidId), name);
        return Result.success();
    }

    @PostMapping("/project")
    @ApiOperation("对项目捐助")
    public Result<Object> project(@RequestBody Map<String, String> map) {
        String projectId = map.get("projectId");
        String amount = map.get("amount");
        donorDonationService.addProject(BaseInfoContext.get().getId(), Long.parseLong(projectId), Integer.parseInt(amount));
        return Result.success();
    }

    @GetMapping("/statistic")
    @ApiOperation("获得捐助统计信息")
    public Result<DonationStaDto> statistic() {
        return Result.success(donorDonationService.getStatistic(BaseInfoContext.get().getId()));
    }

    @PostMapping("/money/list")
    @ApiOperation("获取善款捐赠记录")
    public Result<IPageInfo<KidDonationDto>> getMoney(@RequestBody IPage iPage) {
        return Result.success(donorDonationService.getMoney(iPage, BaseInfoContext.get().getId()));
    }

    @PostMapping("/thing/list")
    @ApiOperation("获取物品捐赠记录")
    public Result<IPageInfo<KidThingDto>> getThing(@RequestBody IPage iPage) {
        return Result.success(donorDonationService.getThing(iPage, BaseInfoContext.get().getId()));
    }

    @PostMapping("/project/list")
    @ApiOperation("获取对项目捐赠记录")
    public Result<IPageInfo<ProjectDonationDto>> getProject(@RequestBody IPage iPage) {
        return Result.success(donorDonationService.getProject(iPage, BaseInfoContext.get().getId()));
    }

}
