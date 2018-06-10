package cn.lframe.order.message;

import cn.lframe.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Spring cloud Stream接收端
 *
 * @author Lframe
 * @create2018 -05 -13 -12:29
 */
@Component
@EnableBinding({StreamOutputClient.class,StreamInputClient.class})  //连接到消息代理//启动绑定到消息传递中间件
// ，在此处它将自动创建将绑定到StreamInputClient.OUTPUT通道目标（即队列，主题）
@Slf4j
public class StreamReceiver {

//    @StreamListener //添加该注解到方法上，用于接收流处理的事件
//    @StreamListener(StreamInputClient.INPUT)
//    public void process(String message) {
//        log.info("StreamReceiver：{}", message);
//    }

    /**
     * 接收OrderDTO对象 消息
     * @param message
     */
    @StreamListener(StreamInputClient.INPUT)
    public void process(OrderDTO message) {
        log.info("OUTPUT下的：StreamReceiver：{}", message);
    }


//    @StreamListener(StreamOutputClient.OURPUT)
//    public void processs(OrderDTO message) {
//        log.info("Input下的：StreamReceiver：{}", message);
//    }

}
