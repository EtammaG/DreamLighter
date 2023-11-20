package com.etammag.dreamlighter.entity.volunteer.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("volun_to_mission")
@ApiModel(value = "VolunToMission对象", description = "志愿者与任务关系")
public class VolunToMission {

    @ApiModelProperty("志愿者ID")
    private Long volunId;

    @ApiModelProperty("任务ID")
    private Long missionId;


}
