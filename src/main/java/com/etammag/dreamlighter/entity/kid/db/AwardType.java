package com.etammag.dreamlighter.entity.kid.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("kid_award_type")
@ApiModel(value = "AwardType对象", description = "奖品的类型")
public class AwardType {

    @ApiModelProperty("唯一ID")
    private Long id;

    @ApiModelProperty("名称")
    private String name;


}
