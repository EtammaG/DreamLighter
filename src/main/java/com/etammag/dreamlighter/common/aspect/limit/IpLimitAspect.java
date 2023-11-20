package com.etammag.dreamlighter.common.aspect.limit;

import com.etammag.dreamlighter.common.annotation.limit.IpLimit;
import com.etammag.dreamlighter.common.entity.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class IpLimitAspect {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${dream-lighter.redis.limit-ip}")
    private String REDIS_PREFIX_LIMIT_IP;

    @Pointcut("@annotation(ipLimit)")
    public void excludeService(IpLimit ipLimit) {
    }

    @Around("excludeService(ipLimit)")
    public Object doAround(ProceedingJoinPoint pjp, IpLimit ipLimit) throws Throwable {

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String key = REDIS_PREFIX_LIMIT_IP
                + request.getServletPath().substring(1).replace('/', ':')
                + ":" + request.getRemoteAddr();
        String times = stringRedisTemplate.opsForValue().get(key);
        int t = times == null ? 1 : Integer.parseInt(times) + 1;
        if (t > (ipLimit.timesInAUnit() == 0 ? ipLimit.value() : ipLimit.timesInAUnit()))
            return Result.error("请求次数过多，请稍后再试");
        stringRedisTemplate.opsForValue().set(key, Integer.toString(t), 1, ipLimit.unit());
        return pjp.proceed();
    }

}