package cn.lframe.order.dto;

import cn.lframe.order.domain.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Lframe
 * @create2018 -05 -03 -8:58
 */
@Data
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    private List<OrderDetail> orderDetailList;


}
