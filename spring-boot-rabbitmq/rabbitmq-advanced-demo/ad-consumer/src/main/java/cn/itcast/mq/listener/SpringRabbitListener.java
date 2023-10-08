package cn.itcast.mq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringRabbitListener {

    /**
     * 模拟消息确认机制
     * 当配置文件中是：acknowledge-mode: none # 关闭ack
     * 这里模拟消息处理异常，消息接收失败后，消息依然被RabbitMQ删除了
     * 对应的是 CommonConfig 这个类
     */
    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue(String msg) {
        log.debug("消费者接收到simple.queue的消息：【" + msg + "】");
        System.out.println(1 / 0);
        log.info("消费者处理消息成功！");
    }

    // /**
    //  * 定义一个新的消费者，并且声明 死信交换机、死信队列
    //  * 对应的是 TTLMessageConfig 这个类
    //  */
    // @RabbitListener(bindings = @QueueBinding(
    //         value = @Queue(name = "dl.queue", durable = "true"),
    //         exchange = @Exchange(name = "dl.direct"), key = "dl"))
    // public void listenDlQueue(String msg) {
    //     log.info("消费者接收到了dl.queue的延迟消息 {}", msg);
    // }

    // @RabbitListener(bindings = @QueueBinding(
    //         value = @Queue(name = "delay.queue", durable = "true"),
    //         exchange = @Exchange(name = "delay.direct", delayed = "true"),
    //         key = "delay"
    // ))
    // public void listenDelayExchange(String msg) {
    //     log.info("消费者接收到了delay.queue的延迟消息");
    // }
}
