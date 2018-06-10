package cn.lframe.product.vo;

import lombok.Data;

/**
 * @author Lframe
 * @create2018 -05 -02 -15:40
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;

}
