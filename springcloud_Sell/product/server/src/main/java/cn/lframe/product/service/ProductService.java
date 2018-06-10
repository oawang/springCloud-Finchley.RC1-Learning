package cn.lframe.product.service;

import cn.lframe.product.common.DecreaseStockInput;
import cn.lframe.product.domain.ProductInfo;
import cn.lframe.product.dto.CartDTO;
import cn.lframe.product.vo.ProductInfoVO;

import java.util.List;

/**
 * @author lframe
 */
public interface ProductService {

    /**
     * 通过种类查询商品列表
     * @param categoryType
     * @return
     */
    List<ProductInfoVO> findByCategoryType(Integer categoryType);

    /**
     * 通过商品状态和种类查询商品信息
     * @param productStatus
     * @param categoryType
     * @return
     */
    List<ProductInfoVO> findByProductStatusAndCategoryType(Integer productStatus, Integer categoryType);

    /**
     * 查询商品列表
     * @param productIdList 商品id集合
     * @return
     */
    List<ProductInfo> findList(List<String> productIdList);


    /**
     * 扣库存
     * @param cartDTOList
     */
    void decreaseStock(List<DecreaseStockInput> cartDTOList);

}
