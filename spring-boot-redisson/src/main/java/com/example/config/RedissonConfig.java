package com.example.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        // 通过读取配置文件的方式进行配置
        //Config config = Config.fromYAML(new File("config-file.yaml"));
        //调用config.toYAML方法可以将一个Config配置实例序列化为一个含有YAML数据类型的字符串：
        //Config config = new Config();
        // ... 省略许多其他的设置
        //String jsonFormat = config.toYAML();

        Config config = new Config();
        // 集群方式配置
        //config.useClusterServers()
        // 集群状态扫描间隔时间，单位是毫秒
        //.setScanInterval(2000)
        //可以用"rediss://"来启用SSL连接 集群
        //.addNodeAddress("redis://127.0.0.1:7000", "redis://127.0.0.1:7001")
        //.addNodeAddress("redis://127.0.0.1:7002");
        // 单机模式
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        // 关于org.redisson.Config类的配置参数
        return Redisson.create(config);
    }
}
