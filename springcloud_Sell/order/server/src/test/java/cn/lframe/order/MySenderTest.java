package cn.lframe.order;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Lframe
 * @create2018 -05 -10 -15:34
 */
@Component
public class MySenderTest extends OrderApplicationTests{

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send(){
        amqpTemplate.convertAndSend("myQueue","now "+new Date());
    }



    @Test
    public void sendOrder(){
        amqpTemplate.convertAndSend("myOrder","computer","now "+new Date());
    }


//    @Test
//    public void send(){
//        amqpTemplate.convertAndSend("myOrder","fruit","fruitOrder","now "+new Date());
//    }
}
