package com.etammag.dreamlighter.entity.kid.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("kid_award")
@ApiModel(value = "Award对象", description = "奖品（供孩子兑换）")
public class Award {

    @ApiModelProperty("唯一ID")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("图片地址")
    private String pic;

    @ApiModelProperty("需要点数")
    private Integer point;

    @ApiModelProperty("库存")
    private Integer stock;

}
