package cn.lframe.product.repository;

import cn.lframe.product.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

}
