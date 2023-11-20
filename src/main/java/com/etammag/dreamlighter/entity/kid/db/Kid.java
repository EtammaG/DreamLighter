package com.etammag.dreamlighter.entity.kid.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("kid")
@ApiModel(value = "Kid对象", description = "被捐助的孩子")
public class Kid {

    @ApiModelProperty("唯一ID")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("孩子照片路径")
    private String photo;

    @ApiModelProperty("学校ID")
    private Long schoolId;

    @ApiModelProperty("年级")
    private Integer grade;

    @ApiModelProperty("住址")
    private String address;

    @ApiModelProperty("简介")
    private String description;

    @ApiModelProperty("详细信息")
    private String detail;

    @ApiModelProperty("总点数（学习点数）")
    private Integer totalPoint;

    @ApiModelProperty("商城点数")
    private Integer mallPoint;

    @ApiModelProperty("上周的total_point")
    private Integer pointWeekAgo;

    @ApiModelProperty("上周的total_point排行")
    private Integer rankWeekAgo;

    @ApiModelProperty("上周的周排名")
    private Integer weekrankWeekAgo;


}
