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
@AllArgsConstructor
@NoArgsConstructor
@TableName("donor_kid_donation")
@ApiModel(value = "KidDonation对象", description = "捐赠者捐助孩子善款记录")
public class KidDonation {

    @ApiModelProperty("捐赠者ID")
    private Long donorId;

    @ApiModelProperty("孩子ID")
    private Long kidId;

    @ApiModelProperty("额度")
    private Integer amount;

    @ApiModelProperty("时间")
    private LocalDateTime time;


}
