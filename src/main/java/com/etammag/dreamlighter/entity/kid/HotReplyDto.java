package com.etammag.dreamlighter.entity.kid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "HotReplyDto", description = "孩子任务提交中的热门提交")
public class HotReplyDto {

    @ApiModelProperty("热门ID")
    private Long hotId;

    @ApiModelProperty("小孩姓名")
    private String kidName;

    @ApiModelProperty("日期")
    private LocalDate date;

    @ApiModelProperty("任务题目")
    private String title;

    @ApiModelProperty("任务描述")
    private String description;

    @ApiModelProperty("任务的难度")
    private String level;

    @ApiModelProperty("提交媒体文件的路径")
    private String media;

    @ApiModelProperty("对提交的评分")
    private Integer score;

    @ApiModelProperty("对提交的评语")
    private String comment;

    @ApiModelProperty("是否已经被当前用户点赞")
    private Boolean liked;

    @ApiModelProperty("赞数")
    private Integer likeNum;
}
