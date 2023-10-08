package cn.itcast.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 声明一个死信队列，并且指定TTL
 * 同时声明一个交换机，与之绑定
 */
// @Configuration
public class TTLMessageConfig {
    @Bean
    public Queue ttlQueue() {
        return QueueBuilder
                .durable("ttl.queue") // 指定队列名称，并持久化
                .ttl(10000)  // 设置队列的超时时间，10秒
                .deadLetterExchange("dl.direct") // 指定死信交换机
                .deadLetterRoutingKey("dl")
                .build();
    }

    /**
     * 声明交换机，将ttl与交换机绑定：
     */
    @Bean
    public DirectExchange ttlDirectExchange() {
        return new DirectExchange("ttl.direct");
    }

    @Bean
    public Binding ttlBinding() {
        return BindingBuilder
                .bind(ttlQueue())
                .to(ttlDirectExchange())
                .with("ttl");
    }
}
