package com.etammag.dreamlighter.entity.volunteer;

import com.etammag.dreamlighter.entity.volunteer.db.Article;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDet extends Article {

    @ApiModelProperty("赞数")
    private Integer likeNum;

    @ApiModelProperty("评论数")
    private Integer commentNum;

}
