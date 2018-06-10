package cn.lframe.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 用来接收MQ消息的
 *
 * @author Lframe
 * @create2018 -05 -10 -14:52
 */
@Slf4j
@Component
public class MqReceiver {


//        1：    @RabbitListener(queues = {"myQueue"})
// （第一种方式需要我们在rabbitMQ中创建名为"myQueue"队列）
//    2：自动创建队列@RabbitListener(queuesToDeclare = @Queue("myQueue"))
//    3：自动创建，Exchange和Queue绑定
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void process(String message) {
        log.info("MqReceiver：{}", message);
    }


    /**
     * 数码供应商服务  接收消息
     *
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",                 //key：进行分组归类，它也是route key，路由
            value = @Queue("computerOrder")
    ))
    public void processComputer(String message) {
        log.info("computer MqReceiver：{}", message);
    }
    /**
     * 水果供应商服务  接收消息
     *
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "fruit",                 //key：进行分组归类，它也是route key，路由
            value = @Queue("fruitOrder")
    ))
    public void processFruit(String message) {
        log.info("fruit MqReceiver：{}", message);
    }

}
