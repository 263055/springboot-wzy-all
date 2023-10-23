package cn.itcast.mq.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 不知道干嘛用的，还么测试，似乎可以删了？
     */
    @Test
    public void testSendMessage2SimpleQueue() {
        // 1.准备消息
        String message = "hello, spring amqp!";
        // 2.准备CorrelationData
        // 2.1.消息ID
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        // 2.2.准备ConfirmCallback
        correlationData.getFuture().addCallback(result -> {
            // 判断结果
            if (result.isAck()) {
                // ACK
                log.debug("消息成功投递到交换机！消息ID: {}", correlationData.getId());
            } else {
                // NACK
                log.error("消息投递到交换机失败！消息ID：{}", correlationData.getId());
                // 重发消息
            }
        }, ex -> {
            // 记录日志
            log.error("消息发送失败！", ex);
            // 重发消息
        });
        // 3.发送消息
        rabbitTemplate.convertAndSend("amq.topic", "a.simple.test", message, correlationData);
    }

    /**
     * 测试延迟队列
     * 但我没有配置这玩意，就先搁置一下吧~
     */
    @Test
    public void testSendDelayMessage() {
        // 1.准备消息
        Message message = MessageBuilder
                .withBody("hello, ttl messsage".getBytes(StandardCharsets.UTF_8))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                .setHeader("x-delay", 5000)
                .build();
        // 2.准备CorrelationData
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        // 3.发送消息
        rabbitTemplate.convertAndSend("delay.direct", "delay", message, correlationData);

        log.info("发送消息成功");
    }

    /**
     * 测试简单的持久化队列
     * 对应 CommonConfig
     * 对应 SpringRabbitListener 下的 listenSimpleQueue
     */
    @Test
    public void testDurableMessage() {
        // 1.准备消息
        Message message = MessageBuilder.withBody("hello, spring".getBytes(StandardCharsets.UTF_8))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                .build();
        // 2.发送消息
        rabbitTemplate.convertAndSend("simple.queue", message);
    }

    /**
     * 测试延迟队列
     * 对应 TTLMessageConfig
     * 对应 SpringRabbitListener 下的 listenDlQueue
     */
    @Test
    public void testTTLMessage() {
        // 1.准备消息
        Message message = MessageBuilder
                .withBody("hello, ttl messsage".getBytes(StandardCharsets.UTF_8))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                // 这里如果不设置过期时间，那么就会根据
                // TTLMessageConfig配置中的ttlQueue方法设置的延迟时间决定
                // 如果设置了过期时间，那么会优先这个过期时间
                .setExpiration("5000")
                .build();
        // 2.发送消息
        rabbitTemplate.convertAndSend("ttl.direct", "ttl", message);
        /*
            // 这里也可以加入 id 标识符
            // 消息ID，需要封装到CorrelationData中
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            // 发送消息
            rabbitTemplate.convertAndSend("ttl.direct", "ttl", message, correlationData);
        */
        // 3.记录日志
        log.info("消息已经成功发送！");
    }

    /**
     * 将消息保存到磁盘中
     * 对应 LazyConfig
     */
    @Test
    public void testLazyQueue() {
        long b = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            // 1.准备消息
            Message message = MessageBuilder
                    .withBody("hello, Spring".getBytes(StandardCharsets.UTF_8))
                    .setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
                    .build();
            // 2.发送消息
            rabbitTemplate.convertAndSend("lazy.queue", message);
        }
        long e = System.nanoTime();
        System.out.println(e - b);
    }

    /**
     * 将消息保存到内存中
     * 每隔一段时间九江消息刷出到磁盘中
     * 对应 LazyConfig
     */
    @Test
    public void testNormalQueue() {
        long b = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            // 1.准备消息
            Message message = MessageBuilder
                    .withBody("hello, Spring".getBytes(StandardCharsets.UTF_8))
                    .setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
                    .build();
            // 2.发送消息
            rabbitTemplate.convertAndSend("normal.queue", message);
        }
        long e = System.nanoTime();
        System.out.println(e - b);
    }
}
