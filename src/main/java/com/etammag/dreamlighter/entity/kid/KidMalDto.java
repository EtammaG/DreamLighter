package com.etammag.dreamlighter.entity.kid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "KidMalDto", description = "孩子商城相关的信息")
public class KidMalDto {

    @ApiModelProperty("商城积分")
    private int mallPoint;

}
