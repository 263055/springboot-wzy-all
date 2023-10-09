package cn.itcast.mq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定义惰性队列 <br/>
 * 已测试 <br/>
 * 队列的主要作用是，将消息存放到硬盘中 <br/>
 */
// @Configuration
public class LazyConfig {

    @Bean
    public Queue lazyQueue() {
        return QueueBuilder
                .durable("lazy.queue")
                .lazy() // 开启 x-queue-mode 为lazy
                .build();
    }

    @Bean
    public Queue normalQueue() {
        return QueueBuilder.durable("normal.queue")
                .build();
    }
}
