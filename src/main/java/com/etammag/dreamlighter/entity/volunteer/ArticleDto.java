package com.etammag.dreamlighter.entity.volunteer;

import com.etammag.dreamlighter.entity.volunteer.db.Article;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "ArticleDto", description = "文章的相关信息")
public class ArticleDto extends Article {
    @ApiModelProperty("文章的评论数")
    private int amount;

}
