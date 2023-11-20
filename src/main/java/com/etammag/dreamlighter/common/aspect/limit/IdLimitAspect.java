package com.etammag.dreamlighter.common.aspect.limit;

import com.etammag.dreamlighter.common.BaseInfoContext;
import com.etammag.dreamlighter.common.annotation.limit.IdLimit;
import com.etammag.dreamlighter.common.entity.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
public class IdLimitAspect {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${dream-lighter.redis.limit-id}")
    private String REDIS_PREFIX_LIMIT_ID;

    @Pointcut("@annotation(idLimit)")
    public void excludeService(IdLimit idLimit) {
    }

    @Around("excludeService(idLimit)")
    public Object doAround(ProceedingJoinPoint pjp, IdLimit idLimit) throws Throwable {
        String key = REDIS_PREFIX_LIMIT_ID + BaseInfoContext.get().getId();
        String times = stringRedisTemplate.opsForValue().get(key);
        int t = times == null ? 1 : Integer.parseInt(times) + 1;
        if (t > (idLimit.timesInAUnit() == 0 ? idLimit.value() : idLimit.timesInAUnit()))
            return Result.error("请求次数过多，请稍后再试");
        stringRedisTemplate.opsForValue().set(key, Integer.toString(t), 1, idLimit.unit());
        return pjp.proceed();
    }

}