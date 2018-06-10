package cn.lframe.product.common;

import lombok.Data;

/**
 * @author Lframe
 * @create2018 -05 -07 -13:11
 */
@Data
public class DecreaseStockInput {

    private String productId;

    private Integer productQuantity;

    public DecreaseStockInput() {
    }

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
