package com.etammag.dreamlighter.entity.donor.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("donor_project")
@ApiModel(value = "Project对象", description = "爱心项目")
public class Project {

    @ApiModelProperty("项目ID")
    private Long id;

    @ApiModelProperty("项目名称")
    private String name;

    @ApiModelProperty("项目简述")
    private String description;

    @ApiModelProperty("项目详情")
    private String detail;

    @ApiModelProperty("项目照片地址")
    private String pic;

    @ApiModelProperty("项目实施地")
    private String location;


}
