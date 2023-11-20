package com.etammag.dreamlighter.entity.donor.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("donor_project_type")
@ApiModel(value = "ProjectType对象", description = "项目类别")
public class ProjectType {

    private Long id;

    @ApiModelProperty("名称")
    private String name;


}
