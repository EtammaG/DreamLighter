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
@TableName("donor_project_donation")
@ApiModel(value = "ProjectDonation对象", description = "捐赠者捐助项目善款记录")
public class ProjectDonation {

    @ApiModelProperty("项目ID")
    private Long projectId;

    @ApiModelProperty("捐助者ID")
    private Long donorId;

    @ApiModelProperty("捐助项目的金额")
    private Integer amount;

    @ApiModelProperty("捐助的时间")
    private LocalDateTime time;


}
