package cn.lframe.order.service.impl;

import cn.lframe.order.domain.OrderDetail;
import cn.lframe.order.domain.OrderMaster;
import cn.lframe.order.dto.OrderDTO;
import cn.lframe.order.enums.OrderStatusEnum;
import cn.lframe.order.enums.PayStatusEnum;
import cn.lframe.order.enums.ResultEnum;
import cn.lframe.order.exception.OrderException;
import cn.lframe.order.repository.OrderDetailRepository;
import cn.lframe.order.repository.OrderMasterRepository;
import cn.lframe.order.service.OrderService;
import cn.lframe.order.util.KeyUtil;
import cn.lframe.product.client.ProductClient;
import cn.lframe.product.common.DecreaseStockInput;
import cn.lframe.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Lframe
 * @create2018 -05 -03 -9:03
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.getUniqueKey();
//        查询商品信息（调用商品服务）
        List<String> productIdList = orderDTO.getOrderDetailList()
                .stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);

//        ①读redis（从redis中查询商品信息）（在商品服务中，我们把商品信息都存在了redis中了）
//        ②计算总价
//        ③减少库存并将新值重新设置到redis中，（上面的步骤需要加redis分布式锁，因为我们的程序是分布式部署的）
//        下面的订单详情入库和订单入库如果失败了，可以使用@Transactional来回滚事务（因为是数据库）。
//        如果订单详情入库和订单入库如果失败了，并且数据库也通过@Transactional进行了事务回滚，
//        ④但是redis并没有事务回滚的方法，所以这里需要我们进行关于redis的手动回滚。
//       （说白了就是订单入库异常等等，手动回滚redis）


        //       计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfoOutput productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    orderAmount = new BigDecimal(orderDetail.getProductQuantity())
                            .multiply(productInfo.getProductPrice())
                            .add(orderAmount);
//                    下面这个方法，及即使被拷贝的对象的属性为null，它也会拷贝进去。
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.getUniqueKey());
//                    订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }

        }
//      扣库存（调用商品服务）
        List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetailList()
                .stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputList);
//     订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
//        用户传进来的参数到目前全部传到了OrderDTO。
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(String orderId) {
//        1：查询订单
        Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderId);

        if (!orderMasterOptional.isPresent()) {
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }
//        2：判断订单状态
        OrderMaster orderMaster = orderMasterOptional.get();

        if (OrderStatusEnum.NEW.getCode() != orderMaster.getOrderStatus()) {
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }

//        3：修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        orderMasterRepository.save(orderMaster);

//        4:查询订单详情
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);

        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new OrderException(ResultEnum.ORDER_DETAIL_ONT_EXIT);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}

