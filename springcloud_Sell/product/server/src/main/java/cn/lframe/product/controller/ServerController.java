package cn.lframe.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lframe
 * @create2018 -05 -03 -8:35
 */
@RestController
public class ServerController {

    @GetMapping("/msg")
    public String msg(){
        return "this is product' msg 0";
    }
}
