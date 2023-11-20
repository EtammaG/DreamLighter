package com.etammag.dreamlighter.entity.kid;

import com.etammag.dreamlighter.entity.kid.db.Kid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "KidDto", description = "孩子的详细信息")
public class KidDto extends Kid {
    @ApiModelProperty("学校名称")
    private String school;
}
