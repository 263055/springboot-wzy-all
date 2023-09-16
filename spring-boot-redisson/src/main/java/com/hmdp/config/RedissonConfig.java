package com.hmdp.config;

import jodd.util.StringUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

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

    // @Bean
    // public RedissonClient redisson(){
    //     return Redisson.create(
    //             Config.fromYAML(new ClassPathResource("single_redisson.yaml").getInputStream()));
    // }


    // @Bean
    // public RedissonClient config() {
        // 通过读取配置文件的方式进行配置
        //Config config = Config.fromYAML(new File("config-file.yaml"));
        //调用config.toYAML方法可以将一个Config配置实例序列化为一个含有YAML数据类型的字符串：
        //Config config = new Config();
        // ... 省略许多其他的设置
        //String jsonFormat = config.toYAML();

        // Config config = new Config();
        // 集群方式配置
        //config.useClusterServers()
        // 集群状态扫描间隔时间，单位是毫秒
        //.setScanInterval(2000)
        //可以用"rediss://"来启用SSL连接 集群
        //.addNodeAddress("redis://127.0.0.1:7000", "redis://127.0.0.1:7001")
        //.addNodeAddress("redis://127.0.0.1:7002");
        // 单机模式
        // config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        // 关于org.redisson.Config类的配置参数
        // RedissonClient redisson = Redisson.create(config);
        // return redisson;
    // }
}
