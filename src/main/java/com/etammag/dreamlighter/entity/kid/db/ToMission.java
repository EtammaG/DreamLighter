package com.etammag.dreamlighter.entity.kid.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@TableName("kid_to_mission")
@ApiModel(value = "ToMission对象", description = "孩子、任务和提交关系")
public class ToMission {

    @ApiModelProperty("孩子ID")
    private Long kidId;

    @ApiModelProperty("任务ID")
    private Long missionId;

    @ApiModelProperty("提交ID，为空说明未提交")
    private Long replyId;


}
