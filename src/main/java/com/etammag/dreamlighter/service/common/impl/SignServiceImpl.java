package com.etammag.dreamlighter.service.common.impl;

import com.alibaba.fastjson.JSON;
import com.etammag.icommon.utils.JwtUtil;
import com.etammag.icommon.entity.BaseInfo;
import com.etammag.dreamlighter.entity.user.IUserDetails;
import com.etammag.dreamlighter.service.common.SignService;
import com.etammag.icommon.context.BaseInfoContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;

@Service
public class SignServiceImpl implements SignService {

    @Resource(name = "donorAuthManager")
    private AuthenticationManager donorAuthManager;

    @Resource(name = "kidAuthManager")
    private AuthenticationManager kidAuthManager;

    @Resource(name = "volunteerAuthManager")
    private AuthenticationManager volunteerAuthManager;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${dream-lighter.redis.login-donor}")
    private String DONOR_KEY_PREFIX;

    @Value("${dream-lighter.redis.login-kid}")
    private String KID_KEY_PREFIX;

    @Value("${dream-lighter.redis.login-volunteer}")
    private String VOLUNTEER_KEY_PREFIX;

    @Value("${dream-lighter.sys.sign-in-duration}")
    private Duration SIGNIN_DURATION;

    @Override
    public String login(String username, String password, int type) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        String keyPrefix;
        Authentication authenticate;
        switch (type) {
            case 1:
                authenticate = donorAuthManager.authenticate(token);
                keyPrefix = DONOR_KEY_PREFIX;
                break;
            case 2:
                authenticate = kidAuthManager.authenticate(token);
                keyPrefix = KID_KEY_PREFIX;
                break;
            case 3:
                authenticate = volunteerAuthManager.authenticate(token);
                keyPrefix = VOLUNTEER_KEY_PREFIX;
                break;
            default:
                return null;

        }
        IUserDetails principal = (IUserDetails) authenticate.getPrincipal();
        String key = keyPrefix + principal.getBaseInfo().getId();
        String jwt = JwtUtil.createJWT(key);
        stringRedisTemplate.opsForValue().set(
                key,
                JSON.toJSONString(principal),
                SIGNIN_DURATION
        );
        return jwt;
    }

    @Override
    public void logout() {
        logout(BaseInfoContext.get());
    }

    public void logout(BaseInfo baseInfo) {
        String keyPrefix;
        switch (baseInfo.getType()) {
            case 1:
                keyPrefix = DONOR_KEY_PREFIX;
                break;
            case 2:
                keyPrefix = KID_KEY_PREFIX;
                break;
            case 3:
                keyPrefix = VOLUNTEER_KEY_PREFIX;
                break;
            default:
                keyPrefix = null;
        }
        stringRedisTemplate.delete(keyPrefix + baseInfo.getId());
    }
}
