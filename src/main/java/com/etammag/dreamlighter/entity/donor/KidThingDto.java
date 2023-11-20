package com.etammag.dreamlighter.entity.donor;

import com.etammag.dreamlighter.entity.donor.db.KidThing;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "KidThingDto", description = "捐赠者捐助孩子物品记录")
public class KidThingDto extends KidThing {

    @ApiModelProperty("孩子姓名")
    private String kidName;

    @ApiModelProperty("孩子照片地址")
    private String kidPhoto;
}
