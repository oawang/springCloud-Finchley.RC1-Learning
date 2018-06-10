package cn.lframe.order.exception;

import cn.lframe.order.enums.ResultEnum;

/**
 * 订单异常
 *
 * @author Lframe
 * @create2018 -05 -03 -10:04
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException (Integer code,String message){
        super(message);
        this.code = code;
    }
    public OrderException (ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

}
