package cn.lframe.product.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Lframe
 * @create2018 -05 -02 -15:06
 */
@Data
public class ProductInfoVO {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private String productDescription;

    private String productIcon;

    public ProductInfoVO(String productId, String productName, BigDecimal productPrice, String productDescription, String productIcon) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productIcon = productIcon;
    }

    public ProductInfoVO() {
    }


}
