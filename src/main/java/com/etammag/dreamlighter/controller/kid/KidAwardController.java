package com.etammag.dreamlighter.controller.kid;

import com.etammag.dreamlighter.common.entity.Result;
import com.etammag.dreamlighter.entity.kid.AwardDto;
import com.etammag.dreamlighter.entity.kid.AwardExchangeDto;
import com.etammag.dreamlighter.entity.kid.db.Award;
import com.etammag.dreamlighter.entity.kid.db.AwardType;
import com.etammag.dreamlighter.service.kid.AwardService;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kid/award")
@PreAuthorize("hasAuthority('KID')")
@Api(tags = "孩子端孩子奖品相关接口")
public class KidAwardController {

    private final AwardService awardService;

    @Autowired
    public KidAwardController(AwardService awardService) {
        this.awardService = awardService;
    }

    @GetMapping("/type")
    @ApiOperation("获取奖品类别列表")
    public Result<List<AwardType>> type() {
        return Result.success(awardService.getAllType());
    }

    @PostMapping("/search")
    @ApiOperation("获取指定奖品列表")
    public Result<IPageInfo<AwardDto>> search(@RequestBody Map<String, String> map) {
        IPage iPage = new IPage(
                Integer.parseInt(map.get("pageNum")),
                Integer.parseInt(map.get("pageSize")),
                map.get("orderBy"));
        return Result.success(awardService.search(iPage, Long.parseLong(map.get("typeId")), map.get("name")));
    }

    @PostMapping("/like")
    @ApiOperation("收藏奖品")
    public Result<Object> addLike(@RequestBody Map<String, String> map) {
        awardService.like(Long.parseLong(map.get("awardId")));
        return Result.success();
    }

    @GetMapping("/like")
    @ApiOperation("获得收藏的奖品")
    public Result<IPageInfo<Award>> getLike(IPage iPage) {
        return Result.success(awardService.getLike(iPage));
    }

    @PostMapping("/exchange")
    @ApiOperation("兑换奖品")
    public Result<Object> addExchange(@RequestBody Map<String, String> map) {
        awardService.exchange(Long.parseLong(map.get("awardId")));
        return Result.success();
    }

    @GetMapping("/exchange")
    @ApiOperation("获得兑换奖品的记录")
    public Result<IPageInfo<AwardExchangeDto>> getExchange(IPage iPage) {
        return Result.success(awardService.getExchange(iPage));
    }


}
