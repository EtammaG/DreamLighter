package com.etammag.dreamlighter.entity.kid;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@ApiModel(value = "KidMisDto", description = "孩子任务相关的信息")
public class KidMisDto {

    @ApiModelProperty("照片地址")
    private String photo;

    @ApiModelProperty("学习积分")
    private int totalPoint;

    @ApiModelProperty("未完成的必修任务数量")
    private int subMissionWaiting;

    @ApiModelProperty("完成的必修任务数量")
    private int subMissionDone;

    @ApiModelProperty("未完成的选修任务数量")
    private int optMissionWaiting;

    @ApiModelProperty("完成的选修任务数量")
    private int optMissionDone;

}
