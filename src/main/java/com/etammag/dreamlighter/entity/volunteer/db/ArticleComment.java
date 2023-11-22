package com.etammag.dreamlighter.entity.volunteer.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@TableName("volun_article_comment")
@ApiModel(value = "ArticleComment对象", description = "文章的评论")
public class ArticleComment {

    @ApiModelProperty("文章ID")
    private Long articleId;

    @ApiModelProperty("评论发布者ID")
    private Long volunId;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("评论时间")
    private LocalDateTime time;


}
