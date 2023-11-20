package com.etammag.dreamlighter.entity.kid;

import com.etammag.dreamlighter.entity.kid.db.Kid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "KidExample", description = "Kid搜索样式")
public class KidExample extends Kid {

    @ApiModelProperty("类别ID")
    private Long typeId;

}
