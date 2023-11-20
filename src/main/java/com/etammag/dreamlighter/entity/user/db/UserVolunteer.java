package com.etammag.dreamlighter.entity.user.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("user_volunteer")
@ApiModel(value = "UserVolunteer对象", description = "志愿者用户")
public class UserVolunteer {

    private Long volunteerId;

    private String username;

    private String password;

}
