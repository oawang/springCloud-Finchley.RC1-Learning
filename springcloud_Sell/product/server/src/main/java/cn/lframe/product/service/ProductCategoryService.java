package cn.lframe.product.service;

import cn.lframe.product.vo.ProductVO;
import cn.lframe.product.vo.ResultVO;

import java.util.List;

public interface ProductCategoryService {

    /**
     * 查找所有商品种类
     */
    ResultVO<List<ProductVO>> findAllProductCategory();





}
