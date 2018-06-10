package cn.lframe.order.vo;

import lombok.Data;

/**
 * @author Lframe
 * @create2018 -05 -03 -10:47
 */
@Data
public class ResultVO<T>{

    private Integer code;

    private String meg;

    private T data;

}
