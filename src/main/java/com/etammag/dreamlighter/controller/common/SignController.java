package com.etammag.dreamlighter.controller.common;

import com.etammag.dreamlighter.common.annotation.limit.IpLimit;
import com.etammag.dreamlighter.common.entity.Result;
import com.etammag.dreamlighter.service.common.SignService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Api(tags = "登录登出相关接口")
public class SignController {

    private final SignService signService;

    @Autowired
    public SignController(SignService signService) {
        this.signService = signService;
    }

    @PostMapping("/signin")
    @PreAuthorize("isAnonymous()")
    @IpLimit(5)
    public Result<String> login(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        String type = map.get("type");
        String jwt = signService.login(username, password, Integer.parseInt(type));
        return jwt == null
                ? Result.error("账号或密码错误")
                : Result.success(jwt);
    }

    @PostMapping("/signout")
    @PreAuthorize("hasAuthority('LOGIN')")
    public Result<Object> logout() {
        signService.logout();
        return Result.success();
    }
}
