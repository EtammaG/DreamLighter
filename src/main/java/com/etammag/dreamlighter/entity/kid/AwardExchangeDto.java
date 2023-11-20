package com.etammag.dreamlighter.entity.kid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel(value = "AwardExchangeDto", description = "奖品的兑换记录")
public class AwardExchangeDto {

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("商品描述")
    private String description;

    @ApiModelProperty("商品图片地址")
    private String pic;

    @ApiModelProperty("花费点数")
    private Integer point;

    @ApiModelProperty("收货地址")
    private String address;

    @ApiModelProperty("兑换时间")
    private LocalDateTime time;


}
