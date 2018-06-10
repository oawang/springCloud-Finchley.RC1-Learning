package cn.lframe.product.enums;

import lombok.Getter;

/**
 * @author Lframe
 * @create2018 -05 -06 -12:47
 */
@Getter
public enum ResultEnum {

    PRODUCT_ONT_EXIT(1,"商品不存在"),

    PRODUCT_STOCK_ERROR(2,"库存有误"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
