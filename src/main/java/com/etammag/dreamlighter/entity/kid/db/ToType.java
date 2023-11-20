package com.etammag.dreamlighter.entity.kid.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("kid_to_type")
@ApiModel(value = "ToType对象", description = "孩子和类别关系")
public class ToType {

    @ApiModelProperty("唯一ID")
    private Long kidId;

    @ApiModelProperty("类别ID")
    private Long typeId;


}
