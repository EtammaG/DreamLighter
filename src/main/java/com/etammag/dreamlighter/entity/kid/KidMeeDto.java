package com.etammag.dreamlighter.entity.kid;

import com.etammag.dreamlighter.entity.kid.db.Kid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "KidMisDto", description = "孩子我的相关的信息")
public class KidMeeDto extends Kid {

    @ApiModelProperty("以获得捐助")
    private int obtainedMoney;

    @ApiModelProperty("total_point排行")
    private int rank;

    @ApiModelProperty("周排名")
    private int weekrank;

}
