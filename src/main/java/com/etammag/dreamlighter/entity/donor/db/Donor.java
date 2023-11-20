package com.etammag.dreamlighter.entity.donor.db;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Donor对象", description = "捐赠者")
public class Donor {

    @ApiModelProperty("唯一ID")
    private Long id;

    @ApiModelProperty("昵称")
    private String nickname;


}
