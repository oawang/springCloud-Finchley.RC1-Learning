package cn.lframe.order.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 创建订单时，前端传递过来的参数
 *
 * @author Lframe
 * @create2018 -05 -03 -9:52
 */
@Data
public class OrderForm {

    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "手机号必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "openid必填")
    private String openid;

    /**
     * 为什么我们这里购物车也是一个字符串呢？
     * 因为前端传递回来的是一个json，但是它也是个字符串，我们拿到的呢，其实是个字符串，拿到之后
     * 呢，再做一些相应的处理，再把它转成对象
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;

}
