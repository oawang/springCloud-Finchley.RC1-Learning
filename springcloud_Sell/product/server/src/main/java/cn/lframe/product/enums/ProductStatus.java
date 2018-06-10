package cn.lframe.product.enums;

import lombok.Getter;

@Getter
public enum ProductStatus {

    PUTAWAY(1,"上架"),
    SOLDOUT(0,"下架"),

    ;

    private Integer code;
    private String message;

    ProductStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
