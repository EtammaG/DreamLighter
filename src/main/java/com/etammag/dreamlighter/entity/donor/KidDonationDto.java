package com.etammag.dreamlighter.entity.donor;

import com.etammag.dreamlighter.entity.donor.db.KidDonation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "KidDonationDto", description = "捐赠者捐助孩子善款记录")
public class KidDonationDto extends KidDonation {

    @ApiModelProperty("孩子姓名")
    private String kidName;

    @ApiModelProperty("孩子照片地址")
    private String kidPhoto;
}
