package com.etammag.dreamlighter.entity.volunteer.db;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@ApiModel(value = "Volunteer对象", description = "志愿者")
public class Volunteer {

    @ApiModelProperty("唯一ID")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("0表示女性，1表示男性")
    private Integer gender;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("生日")
    private LocalDate birthday;

    @ApiModelProperty("志愿者当前所在地区")
    private String area;

    @ApiModelProperty("志愿者职业")
    private String job;

    @ApiModelProperty("志愿者家乡")
    private String hometown;

    @ApiModelProperty("照片地址")
    private String photo;
}
