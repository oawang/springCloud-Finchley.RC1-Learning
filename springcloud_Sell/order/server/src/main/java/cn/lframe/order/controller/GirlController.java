package cn.lframe.order.controller;

import cn.lframe.order.config.GirlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lframe
 * @create2018 -05 -09 -10:54
 */
@RestController
public class GirlController {

    @Autowired
    private GirlConfig girlConfig;

    @GetMapping("/girl/print")
    @RefreshScope
    public String print(){
        return "name:" + girlConfig.getName() +" age:" + girlConfig.getAge();
    }
}
