package com.etammag.dreamlighter.entity.mission.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@TableName("kid_mission")
@ApiModel(value = "Mission对象", description = "孩子的任务")
public class Mission {
    @ApiModelProperty("唯一ID")
    private Long id;

    @ApiModelProperty("标签")
    private String tag;

    @ApiModelProperty("任务题目")
    private String title;

    @ApiModelProperty("任务描述")
    private String description;

    @ApiModelProperty("0表示选做，1表示必做")
    private Integer type;

    @ApiModelProperty("任务的难度")
    private String level;

    @ApiModelProperty("题目的点数")
    private Integer point;

    @ApiModelProperty("日期")
    private LocalDate date;


}
