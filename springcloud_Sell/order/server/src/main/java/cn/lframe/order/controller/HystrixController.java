package cn.lframe.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author Lframe
 * @create2018 -05 -20 -11:59
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    /**
     * 服务降级，当我们的order服务调用product服务的时候，而product服务挂掉了，此时，
     * 我们是使用@HystrixCommand(fallbackMethod = "fallback"),就把该服务转交给了fallback（）方法了。
     * <p>
     * 我们也可以在合适的时候，在getProductInfoList()方法中抛出异常，
     * 然后通过降级（@HystrixCommand(fallbackMethod = "fallback")）仍然能够处理。
     * 也就是不一定非得雪崩的情况下使用服务降级，我们也可以设定自己的降级。
     *
     * @return
     */
//    @HystrixCommand(fallbackMethod = "fallback")//服务降级

//    超时配置
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(
//                    name = "execution.isolation.thread.timeoutInMilliseconds",
//            value = "3000")
//    })
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否启动断路器
//            时间窗口的阀值。这里的阀值是10。
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//            当启动断路器后的时间达到设定的毫秒数时（这里是10s），允许用户重试请求服务。
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//几毫秒之后允许重试
            //断路器的错误阀值百分比，如果超过设定的值（60%），它就会启动断路器。启动服务降级。
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
    })
    @GetMapping("/getProductInfoList")
    public String getProductInfoList(@RequestParam("number") Integer number) {
        if (number % 2 == 0) {
            return "success";
        }
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.postForObject("http://localhost:8090/listForOrder",
                Arrays.asList("123456"), String.class);
    }

    private String fallback() {
        return "太拥挤了，请稍后再试。。。。";
    }


    private String defaultFallback() {
        return "默认提示：太拥挤了，请稍后再试。。。。";
    }


}
