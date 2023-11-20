package com.etammag.dreamlighter.common.utils.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class CacheUtil {

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public CacheUtil(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public <V> V tryValue(Supplier<V> db, String key, Duration duration, Class<V> vClass) {
        String json = stringRedisTemplate.opsForValue().get(key);
        if (json != null) return JSON.parseObject(json, vClass);
        V value = db.get();
        if(value == null) stringRedisTemplate.opsForValue().set(key, "", duration);
        else stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(value), duration);
        return value;
    }

    // 类型擦除真是天才
    public <V> List<V> tryValueList(Supplier<List<V>> db, String key, Duration duration, Class<V> vClass) {
        String json = stringRedisTemplate.opsForValue().get(key);
        if (json != null) return JSON.parseArray(json, vClass);
        List<V> value = db.get();
        if(value == null) stringRedisTemplate.opsForValue().set(key, "", duration);
        else stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(value), duration);
        return value;
    }

    public <V> List<V> tryList(Supplier<List<V>> db, String key, Duration duration, Class<V> vClass) {
        // 使用另一个value类型实现缓存空对象解决缓存穿透
        String exitKey = key + ":e";
        if(stringRedisTemplate.opsForValue().get(exitKey) != null) return new LinkedList<>();
        Long size = stringRedisTemplate.opsForList().size(key);
        if(size != null && size != 0) {
            List<String> jsons = stringRedisTemplate.opsForList().range(key, 0, size);
            if(jsons == null) return new LinkedList<>();
            return jsons.stream().map((json) -> JSON.parseObject(json, vClass)).collect(Collectors.toList());
        }
        List<V> value = db.get();
        if(value == null || value.isEmpty()) {
            stringRedisTemplate.opsForValue().set(exitKey, "", duration);
            return new LinkedList<>();
        }
        stringRedisTemplate.opsForList().rightPushAll(key, value.stream().map(JSON::toJSONString).collect(Collectors.toList()));
        return value;
    }

}
