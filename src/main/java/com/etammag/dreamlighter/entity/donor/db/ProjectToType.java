package com.etammag.dreamlighter.entity.donor.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("donor_project_to_type")
@ApiModel(value = "ProjectToType对象", description = "项目和类别关系")
public class ProjectToType {

    @ApiModelProperty("项目ID")
    private Long projectId;

    @ApiModelProperty("类别ID")
    private Long typeId;


}
