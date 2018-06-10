package cn.lframe.order.service;

import cn.lframe.order.dto.OrderDTO;

/**
 * @author Lframe
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);


    /**
     * 完结订单（只能由卖家操作）
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);
}
