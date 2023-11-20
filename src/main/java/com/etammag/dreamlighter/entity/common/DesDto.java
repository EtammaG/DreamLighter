package com.etammag.dreamlighter.entity.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@ApiModel(value = "DesDto", description = "系统的宣传信息")
public class DesDto {
    @ApiModelProperty("总捐助金额")
    private int donationAmount;
    @ApiModelProperty("总帮助儿童数")
    private int helpedAmount;
}
