package com.etammag.dreamlighter.entity.mission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "MissionDto", description = "任务概述")
public class MissionKidViewDto {

    @ApiModelProperty("任务ID")
    private Long missionId;

    @ApiModelProperty("标签")
    private String tag;

    @ApiModelProperty("0表示选做，1表示必做")
    private Integer type;

    @ApiModelProperty("题目的点数")
    private Integer point;

    @ApiModelProperty("是否已完成")
    private boolean done;

    @ApiModelProperty("是否被评分")
    private boolean checked;

    @ApiModelProperty("评分")
    private Integer score;

}
