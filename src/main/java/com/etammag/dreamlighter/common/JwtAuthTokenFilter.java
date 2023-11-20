package com.etammag.dreamlighter.common;

import com.alibaba.fastjson.JSON;
import com.etammag.dreamlighter.common.utils.JwtUtil;
import com.etammag.dreamlighter.entity.user.IUserDetails;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;

@Slf4j
@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${dream-lighter.sys.sign-in-duration}")
    private Duration SIGNIN_DURATION;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        String redisKey;
        try {
            redisKey = JwtUtil.parseJWT(token).getBody().getSubject();
        } catch (JwtException e) {
            log.warn("illegal token");
            filterChain.doFilter(request, response);
            return;
        }
        IUserDetails userDetails = JSON.parseObject(stringRedisTemplate.opsForValue().get(redisKey), IUserDetails.class);
        if (userDetails == null) {
            log.warn("illegal token");
            filterChain.doFilter(request, response);
            return;
        }

        stringRedisTemplate.expire(redisKey, SIGNIN_DURATION);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
        BaseInfoContext.set(userDetails.getBaseInfo());
        filterChain.doFilter(request, response);
    }

}
