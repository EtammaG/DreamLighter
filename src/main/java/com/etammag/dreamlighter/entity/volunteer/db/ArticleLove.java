package com.etammag.dreamlighter.entity.volunteer.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@TableName("volun_article_love")
@ApiModel(value = "ArticleLove对象", description = "文章点赞")
public class ArticleLove {

    @ApiModelProperty("志愿者id")
    private Long volunId;

    @ApiModelProperty("文章id")
    private Long articleId;

}
