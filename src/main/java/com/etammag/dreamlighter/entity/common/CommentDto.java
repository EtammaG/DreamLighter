package com.etammag.dreamlighter.entity.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "CommentDto", description = "评论")
public class CommentDto {

    @ApiModelProperty("评论的内容")
    private String content;

    @ApiModelProperty("评论作者的姓名")
    private String name;

    @ApiModelProperty("评论作者的头像")
    private String photo;

    @ApiModelProperty("评论时间")
    private LocalDateTime time;

}
