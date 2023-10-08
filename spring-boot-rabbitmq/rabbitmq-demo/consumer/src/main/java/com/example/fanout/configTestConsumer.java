package com.example.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author : wzy
 * @since : 2023/10/8 10:25
 * <p>
 * 这里是根据 config 的配置进行队列绑定 <br/>
 * 然后再通过测试类发送消息 <br/>
 * 运行结果是：控制台输出两个消息
 * <p>
 * Created in IDEA
 */
@Component
public class configTestConsumer {
    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String msg){
        System.out.println("消费者1接收到Fanout消息：【"+msg+"】");
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String msg){
        System.out.println("消费者2接收到Fanout消息：【"+msg+"】");
    }
}
