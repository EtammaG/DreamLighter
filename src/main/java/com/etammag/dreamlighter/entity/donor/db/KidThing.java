package com.etammag.dreamlighter.entity.donor.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("donor_kid_thing")
@ApiModel(value = "KidThing对象", description = "捐赠者捐助孩子物品记录")
public class KidThing {

    @ApiModelProperty("捐赠者ID")
    private Long donorId;

    @ApiModelProperty("孩子ID")
    private Long kidId;

    @ApiModelProperty("物品名称")
    private String thingName;

    @ApiModelProperty("捐赠时间")
    private LocalDateTime time;


}
