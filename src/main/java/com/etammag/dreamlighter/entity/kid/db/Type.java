package com.etammag.dreamlighter.entity.kid.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("kid_type")
@ApiModel(value = "Type对象", description = "孩子类别")
public class Type {

    @ApiModelProperty("唯一ID")
    private Long id;

    @ApiModelProperty("名称")
    private String name;


}
