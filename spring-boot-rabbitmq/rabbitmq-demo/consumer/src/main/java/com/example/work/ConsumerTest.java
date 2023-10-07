package com.example.work;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.time.LocalTime;

/**
 * @author : wzy
 * @since : 2023/10/7 17:09
 * <p>
 *
 * <p>
 * Created in IDEA
 */
public class ConsumerTest {
    /**
     * 模拟消息堆积
     * 此时会发现消费者2的处理业务时间过程
     * 解决方能：注解
     * prefetch: 1 # 每次只能获取一条消息，处理完成才能获取下一个消息
     */
    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueue1(String msg)throws InterruptedException{
        System.out.println("消费者1接收到消息：【"+msg+"】"+ LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueue2(String msg)throws InterruptedException{
        System.err.println("消费者2........接收到消息：【"+msg+"】"+LocalTime.now());
        Thread.sleep(200);
    }
}
