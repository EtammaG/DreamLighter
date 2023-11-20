package com.etammag.dreamlighter.entity.kid.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@TableName("kid_award_exchange")
@ApiModel(value = "AwardExchange对象", description = "奖品的兑换记录")
public class AwardExchange {

    @ApiModelProperty("奖品ID")
    private Long awardId;

    @ApiModelProperty("孩子ID")
    private Long kidId;

    @ApiModelProperty("收货地址")
    private String address;

    @ApiModelProperty("兑换时间")
    private LocalDateTime time;


}
