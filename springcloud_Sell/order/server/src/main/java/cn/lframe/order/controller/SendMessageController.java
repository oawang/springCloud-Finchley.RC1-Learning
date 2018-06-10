package cn.lframe.order.controller;

import cn.lframe.order.dto.OrderDTO;
import cn.lframe.order.message.StreamInputClient;
import cn.lframe.order.message.StreamOutputClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 发送消息
 *
 * @author Lframe
 * @create2018 -05 -13 -12:33
 */
@RestController
public class SendMessageController {


    @Autowired
    private StreamOutputClient streamOutputClient;

//    @GetMapping("/sendMessage")
//    public void process() {
//        String message = "now " + new Date();
//        streamOutputClient.output().send(MessageBuilder.withPayload(message).build());
//    }


    /**
     * 发送OrderDTO
     */
    @GetMapping("/sendMessage")
    public void process() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("123456");
        streamOutputClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}