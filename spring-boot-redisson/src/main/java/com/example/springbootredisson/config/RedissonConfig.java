package com.example.springbootredisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 这里由于配置读取先后的问题
 * 并不能将统一配置文件中的 ${wzy.redis.host} 正确的映射为 ${spring.redis.host}
 */
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
        System.out.println(host);
        System.out.println(port);
        System.out.println(123456789);
        System.out.println(password);
        //配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + port).setPassword(password);
        //创建对并且返回
        return Redisson.create(config);
    }
}
