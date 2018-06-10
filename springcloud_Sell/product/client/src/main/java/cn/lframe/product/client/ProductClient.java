package cn.lframe.product.client;

import cn.lframe.product.common.DecreaseStockInput;
import cn.lframe.product.common.ProductInfoOutput;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 定义好要定义的接口
 *
 * @author Lframe
 * @FeignClient(name = "product") 表示注册到服务注册中心的Application。
 * @create2018 -05 -03 -16:17
 */
@FeignClient(name = "PRODUCT", fallback = ProductClient.ProductClientFallback.class)//feign和服务降级的一起使用。
public interface ProductClient {

    @GetMapping("/msg")
    String productMsg();

    @PostMapping("/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);


    @PostMapping("/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);


    /**
     * 服务降级的实现类（也就是说，在其他类调用product服务的时候，
     * 而product服务挂掉了，就会调用下面这个类中的相同的方法。）
     */
    @Component
    class ProductClientFallback implements ProductClient {
        @Override
        public String productMsg() {
            return null;
        }

        @Override
        public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
            return null;
        }

        @Override
        public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

        }
    }

}
