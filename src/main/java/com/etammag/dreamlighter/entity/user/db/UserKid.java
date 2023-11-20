package com.etammag.dreamlighter.entity.user.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@TableName("user_kid")
@ApiModel(value = "UserKid对象", description = "孩子用户")
public class UserKid {

    private Long kidId;

    private String username;

    private String password;

}
