package cn.lframe.order.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author Lframe
 * @create2018 -05 -13 -16:11
 */
public interface StreamOutputClient {

    String OUTPUT = "output";

    /**
     * 用于出站
     *
     * @return
     */
    @Output(StreamOutputClient.OUTPUT)
    MessageChannel output();


}
