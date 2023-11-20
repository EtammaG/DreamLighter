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
@TableName("kid_award_like")
@ApiModel(value = "AwardLike对象", description = "奖品的点赞")
public class AwardLike {

    @ApiModelProperty("奖品ID")
    private Long awardId;

    @ApiModelProperty("点赞孩子的ID")
    private Long kidId;


}
