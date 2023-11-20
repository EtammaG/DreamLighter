package com.etammag.dreamlighter.common.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class SimpleLock implements ILock {

    private final String LOCK_PREFIX;
    private final String UUID;

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public SimpleLock(
            @Value("${dream-lighter.redis.lock}") String lockPrefix,
            @Value("${dream-lighter.sys.uuid}") String uuid,
            StringRedisTemplate stringRedisTemplate) {
        LOCK_PREFIX = lockPrefix;
        UUID = uuid;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean tryLock(String lockName, Duration duration) {
        return Boolean.TRUE.equals(stringRedisTemplate.opsForValue().setIfAbsent(
                LOCK_PREFIX + lockName,
                UUID + Thread.currentThread().getId(),
                duration
        ));
    }

    @Override
    public void unlock(String lockName) {
        if ((UUID + Thread.currentThread().getId())
                .equals(stringRedisTemplate.opsForValue().get(LOCK_PREFIX + lockName)))
            stringRedisTemplate.delete(LOCK_PREFIX + lockName);
    }
}
