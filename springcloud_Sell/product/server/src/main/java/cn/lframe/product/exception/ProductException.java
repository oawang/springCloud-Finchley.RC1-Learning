package cn.lframe.product.exception;

import cn.lframe.product.enums.ResultEnum;

/**
 * @author Lframe
 * @create2018 -05 -06 -12:44
 */
public class ProductException extends RuntimeException{

    private Integer code;

    public ProductException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
