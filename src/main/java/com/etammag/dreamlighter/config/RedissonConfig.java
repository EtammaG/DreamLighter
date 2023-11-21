package com.etammag.dreamlighter.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    private final String address;
    private final String port;
    private final String password;

    public RedissonConfig(
            @Value("${spring.redis.host}") String address,
            @Value("${spring.redis.port}") String port,
            @Value("${spring.redis.password}") String password
    ) {
        this.address = address;
        this.port = port;
        this.password = password;
    }

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + address + ":" + port)
                .setPassword(password);
        return Redisson.create(config);
    }
}
