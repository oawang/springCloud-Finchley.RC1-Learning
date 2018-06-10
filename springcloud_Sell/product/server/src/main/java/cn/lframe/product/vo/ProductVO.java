package cn.lframe.product.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品列表
 *
 * @author Lframe
 * @create2018 -05 -02 -14:38
 */
@Data
public class ProductVO {

    @JsonProperty("name")
    private String productCategoryName;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoList;


    public ProductVO(String productCategoryName, Integer type, List<ProductInfoVO> productInfoList) {
        this.productCategoryName = productCategoryName;
        this.type = type;
        this.productInfoList = productInfoList;
    }

    public ProductVO() {

    }
}
