package com.etammag.dreamlighter.entity.volunteer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDto extends ArticleDet {

    @ApiModelProperty("是否已经被当前用户点赞")
    private Boolean liked;

}
