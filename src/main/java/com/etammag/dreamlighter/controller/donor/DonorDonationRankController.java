package com.etammag.dreamlighter.controller.donor;

import com.etammag.icommon.entity.Result;
import com.etammag.dreamlighter.entity.donor.DonationStaDto;
import com.etammag.dreamlighter.service.donor.DonorDonationRankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/donor/donation/rank")
@PreAuthorize("hasAuthority('DONOR')")
@Api(tags = "捐赠者端捐助排行榜相关接口")
public class DonorDonationRankController {

    private final DonorDonationRankService donorDonationRankService;

    @Autowired
    public DonorDonationRankController(DonorDonationRankService donorDonationRankService) {
        this.donorDonationRankService = donorDonationRankService;
    }

    @GetMapping("/all")
    @ApiOperation("总排行")
    public Result<List<DonationStaDto>> all() {
        return Result.success(donorDonationRankService.all());
    }

    @GetMapping("/month")
    @ApiOperation("月排行")
    public Result<List<DonationStaDto>> month() {
        return Result.success(donorDonationRankService.month());
    }

    @GetMapping("/week")
    @ApiOperation("周排行")
    public Result<List<DonationStaDto>> week() {
        return Result.success(donorDonationRankService.week());
    }

}
