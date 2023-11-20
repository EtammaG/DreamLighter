package com.etammag.dreamlighter.entity.kid.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("kid_reply")
@ApiModel(value = "Reply对象", description = "对任务的提交")
public class Reply {

    @ApiModelProperty("唯一ID")
    private Long id;

    @ApiModelProperty("提交媒体文件的路径")
    private String media;

    @ApiModelProperty("对提交的评分")
    private Integer score;

    @ApiModelProperty("对提交的评语")
    private String comment;


}
