package com.etammag.dreamlighter.config;

import com.etammag.icommon.exception.FilterExceptionFilter;
import com.etammag.icommon.limit.IdLimitAspect;
import com.etammag.icommon.limit.IpLimitAspect;
import com.etammag.icommon.security.JwtAuthTokenFilter;
import com.etammag.icommon.utils.redis.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;

@Configuration
public class ICommonConfig {

    private final Duration signInDuration;
    private final String ipLimitKey;
    private final String idLimitKey;
    private final StringRedisTemplate stringRedisTemplate;


    @Autowired
    public ICommonConfig(
            @Value("${dream-lighter.sys.sign-in-duration}") Duration signInDuration,
            @Value("${dream-lighter.redis.limit.ip}") String ipLimitKey,
            @Value("${dream-lighter.redis.limit.id}") String idLimitKey,
            StringRedisTemplate stringRedisTemplate) {
        this.signInDuration = signInDuration;
        this.ipLimitKey = ipLimitKey;
        this.idLimitKey = idLimitKey;
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Bean
    public FilterExceptionFilter filterExceptionFilter() {
        return new FilterExceptionFilter();
    }

    @Bean
    public JwtAuthTokenFilter jwtAuthTokenFilter() {
        return new JwtAuthTokenFilter(stringRedisTemplate, signInDuration);
    }

    @Bean
    public CacheUtil cacheUtil() {
        return new CacheUtil(stringRedisTemplate);
    }

    @Bean
    public IdLimitAspect idLimitAspect() {
        return new IdLimitAspect(stringRedisTemplate, idLimitKey);
    }

    @Bean
    public IpLimitAspect ipLimitAspect() {
        return new IpLimitAspect(stringRedisTemplate, ipLimitKey);
    }

}
