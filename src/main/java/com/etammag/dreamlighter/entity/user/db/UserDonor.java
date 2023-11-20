package com.etammag.dreamlighter.entity.user.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("user_donor")
@ApiModel(value = "UserDonor对象", description = "捐赠者用户")
public class UserDonor {

    private Long donorId;

    private String username;

    private String password;

}
