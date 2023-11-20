package com.etammag.dreamlighter.entity.donor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "ProjectSimDto", description = "项目简单信息")
public class ProjectSimDto {

    @ApiModelProperty("项目ID")
    private Long id;

    @ApiModelProperty("项目名称")
    private String name;

    @ApiModelProperty("项目简述")
    private String description;

    @ApiModelProperty("项目照片地址")
    private String pic;

    @ApiModelProperty("项目实施地")
    private String location;
}
