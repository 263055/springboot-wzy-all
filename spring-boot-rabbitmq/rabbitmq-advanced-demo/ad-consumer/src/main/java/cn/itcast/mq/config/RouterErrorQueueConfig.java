package cn.itcast.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : wzy
 * @since : 2023/10/8 15:52
 * <p>
 * 给simple.queue添加一个死信交换机，给死信交换机绑定一个队列 <br/>
 * 这样消息变成死信后也不会丢弃，而是最终投递到死信交换机，<br/>
 * 路由到与死信交换机绑定的队列
 * <p>
 * Created in IDEA
 */
@Configuration
public class RouterErrorQueueConfig {
    // 声明普通的 simple.queue队列，并且为其指定死信交换机：dl.direct
    @Bean
    public Queue simpleQueue2() {
        return QueueBuilder.durable("simple.queue") // 指定队列名称，并持久化
                .deadLetterExchange("dl.direct") // 指定死信交换机
                .build();
    }

    // 声明死信交换机 dl.direct
    @Bean
    public DirectExchange dlExchange() {
        return new DirectExchange("dl.direct", true, false);
    }

    // 声明存储死信的队列 dl.queue
    @Bean
    public Queue dlQueue() {
        return new Queue("dl.queue", true);
    }

    // 将死信队列 与 死信交换机绑定
    @Bean
    public Binding dlBinding() {
        return BindingBuilder
                .bind(dlQueue())
                .to(dlExchange())
                .with("simple");
    }
}
