package com.etammag.dreamlighter.entity.mission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "MissionVolViewDto", description = "志愿者端中待批列表中的任务展示信息")
public class MissionVolViewDto {

    @ApiModelProperty("该任务的id")
    private Long id;
    @ApiModelProperty("该任务的标题")
    private String title;
    @ApiModelProperty("该任务提交总数")
    private int totalSubmit;
    @ApiModelProperty("该任务未批改数")
    private int waitingCheck;

}
