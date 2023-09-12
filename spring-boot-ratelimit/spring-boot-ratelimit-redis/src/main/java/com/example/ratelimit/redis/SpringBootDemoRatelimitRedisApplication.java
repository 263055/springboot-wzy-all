package com.example.ratelimit.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * <p>
 * 启动器
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019-09-30 09:32
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SpringBootDemoRatelimitRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoRatelimitRedisApplication.class, args);
    }

}
