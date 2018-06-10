package cn.lframe.order.repository;

import cn.lframe.order.domain.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
}
