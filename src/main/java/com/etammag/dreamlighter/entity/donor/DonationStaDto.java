package com.etammag.dreamlighter.entity.donor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@ApiModel(value = "DonationStaDto", description = "捐助统计信息")
public class DonationStaDto {

    @ApiModelProperty("捐赠者ID")
    private Long donorId;

    @ApiModelProperty("捐赠者昵称")
    private String nickname;

    @ApiModelProperty("捐助次数")
    private Integer times;

    @ApiModelProperty("捐赠金额")
    private Integer amount;

    @ApiModelProperty("排行")
    private Integer rank;
}
