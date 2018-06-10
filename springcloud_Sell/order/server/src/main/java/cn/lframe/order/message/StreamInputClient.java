package cn.lframe.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface StreamInputClient {



    String INPUT = "input";


//    String OUTPUT = "output";
    /**
     * 用于入站.（生产者）
     * 一个 SubscribableChannel 绑定到了input。
     *
     * @return
     */
    @Input(StreamInputClient.INPUT)
    SubscribableChannel input();


    /**
     * 用于出站
     *
     * @return
     */
//    @Output(StreamInputClient.OUTPUT)
//    MessageChannel output();




}
