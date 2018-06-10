package cn.lframe.product.service.impl;

import cn.lframe.product.domain.ProductCategory;
import cn.lframe.product.enums.ProductStatus;
import cn.lframe.product.repository.ProductCategoryRepository;
import cn.lframe.product.service.ProductCategoryService;
import cn.lframe.product.service.ProductService;
import cn.lframe.product.util.ResultUtil;
import cn.lframe.product.vo.ProductVO;
import cn.lframe.product.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lframe
 * @create2018 -05 -02 -14:36
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Autowired
    private ProductService productService;

    /**
     * ①查询所有种类
     * ②查询每个种类下的所有上架商品
     * ③构造数据
     * @return
     */
    @Override
    public ResultVO<List<ProductVO>> findAllProductCategory() {

        return ResultUtil.success(
                repository.findAll()
                        .stream()
                        .map(this::productCategoryToProductVO)
                        .collect(Collectors.toList())
        );
    }

    private ProductVO productCategoryToProductVO(ProductCategory productCategory) {
        return new ProductVO(
                productCategory.getCategoryName(),
                productCategory.getCategoryType(),
                productService.findByProductStatusAndCategoryType(
                        ProductStatus.PUTAWAY.getCode(),
                        productCategory.getCategoryType())

        );
    }


}
