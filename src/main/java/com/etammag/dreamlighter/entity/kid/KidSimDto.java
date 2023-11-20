package com.etammag.dreamlighter.entity.kid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "KidSimDto", description = "孩子简单信息")
public class KidSimDto {

    @ApiModelProperty("唯一ID")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("孩子照片路径")
    private String photo;

    @ApiModelProperty("简介")
    private String description;

    @ApiModelProperty("地址")
    private String address;
}
