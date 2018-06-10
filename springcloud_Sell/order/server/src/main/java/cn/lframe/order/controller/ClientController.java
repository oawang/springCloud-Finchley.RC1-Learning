package cn.lframe.order.controller;


import cn.lframe.product.client.ProductClient;
import cn.lframe.product.common.DecreaseStockInput;
import cn.lframe.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author Lframe
 * @create2018 -05 -03 -12:44
 */
@RestController
@Slf4j
public class ClientController {

    /**
     * 这个是IDEA的bug，不影响程序运行
     */
    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg(){

        String response = productClient.productMsg();

        log.info("response={}",response);
        return response;
    }

    @GetMapping("/getProductList")
    public String getProductList(){
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(Arrays.asList("123456","123457","123458"));
        log.info("response={}",productInfoList);
        return "ok";
    }


    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock(){
        productClient.decreaseStock(Arrays.asList(new DecreaseStockInput("123456",2)));
        return "ok";
    }


}
