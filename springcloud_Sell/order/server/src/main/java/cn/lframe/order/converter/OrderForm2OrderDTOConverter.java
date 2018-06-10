package cn.lframe.order.converter;

import cn.lframe.order.domain.OrderDetail;
import cn.lframe.order.dto.OrderDTO;
import cn.lframe.order.enums.ResultEnum;
import cn.lframe.order.exception.OrderException;
import cn.lframe.order.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**OrderForm 转化成OrderDTO
 * @author Lframe
 * @create2018 -05 -03 -10:12
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm){

        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetails = new ArrayList<>();

        try {
            orderDetails = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());

        }catch (Exception e){
            log.error("【json转换】错误，string={}",orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }
}
