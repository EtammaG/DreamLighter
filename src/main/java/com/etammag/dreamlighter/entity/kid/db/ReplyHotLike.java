package com.etammag.dreamlighter.entity.kid.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@TableName("kid_reply_hot_like")
@ApiModel(value = "ReplyHotLike对象", description = "热门提交成果的点赞")
public class ReplyHotLike {

    @ApiModelProperty("热门提交成果的热门ID")
    private Long hotId;

    @ApiModelProperty("点赞孩子的ID")
    private Long kidId;


}
