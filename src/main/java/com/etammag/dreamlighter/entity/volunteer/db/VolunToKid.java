package com.etammag.dreamlighter.entity.volunteer.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@TableName("volun_to_kid")
@ApiModel(value = "VolunToKid对象", description = "志愿者、孩子的对应关系")
public class VolunToKid {

    @ApiModelProperty("志愿者ID")
    private Long volunId;

    @ApiModelProperty("孩子ID")
    private Long kidId;


}
