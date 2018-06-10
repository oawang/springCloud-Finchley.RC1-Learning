package cn.lframe.product.repository;

import cn.lframe.product.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author lframe
 *
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

     List<ProductInfo> findByCategoryType(Integer categoryType);

    List<ProductInfo> findByProductStatusAndCategoryType(Integer productStatus, Integer categoryType);

    List<ProductInfo> findByProductIdIn(List<String> productIdList);



}
