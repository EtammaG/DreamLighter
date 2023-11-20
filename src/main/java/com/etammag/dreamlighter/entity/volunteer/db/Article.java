package com.etammag.dreamlighter.entity.volunteer.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@TableName("volun_article")
@ApiModel(value = "Article对象", description = "志愿者社区文章")
public class Article {

    @ApiModelProperty("唯一ID")
    private Long id;

    @ApiModelProperty("作者名称")
    private String authorName;

    @ApiModelProperty("内容图片地址")
    private String contentPic;

    @ApiModelProperty("作者图片地址")
    private String authorPic;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("发布时间")
    private LocalDateTime articleTime;


}
