package com.hmdp.config;

import jodd.util.StringUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private String port;
    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        if (StringUtil.isEmpty(password)) {
            // 如果是本地环境, redis 的 password 为空
            // 此时不能包含 setPassword
            config.useSingleServer().setAddress("redis://" + host + ":" + port);
        } else {
            config.useSingleServer().setAddress("redis://" + host + ":" + port).setPassword(password);
        }
        //创建对并且返回
        return Redisson.create(config);
    }
}
