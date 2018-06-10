package cn.lframe.product.controller;



import cn.lframe.product.common.DecreaseStockInput;
import cn.lframe.product.dto.CartDTO;
import cn.lframe.product.service.ProductCategoryService;
import cn.lframe.product.service.ProductService;
import cn.lframe.product.vo.ResultVO;
import cn.lframe.product.domain.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Lframe
 * @create2018 -05 -02 -13:43
 */
@RestController
public class ProductController {

    @Autowired
    private ProductCategoryService productCategoryService;


    @Autowired
    private ProductService productService;

//    @CrossOrigin(allowCredentials = "true")allowCredentials设置为true代表允许cookie跨域
    @GetMapping("/sell/buyer/product/list")
    public ResultVO productList(HttpServletRequest httpServletRequest){
        return productCategoryService.findAllProductCategory();

    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList){
        productService.decreaseStock(decreaseStockInputList);
    }

    /**
     * 获取商品列表（给订单服务用的）
     * @param productIdList
     * @return
     */
//    @RequestParam
//    @RequestHeader 作用于请求端的请求头中  作用的数据源只能是参数类型
//    @RequestBody 作用于请求端的请求体中（也就是Body中）。也方法必须是post，不能是get方法。 作用的数据源只能是参数类型
//    @RequestAttribute     作用的数据源只能是参数类型
//    @RequestPart 用于multipart/form-data的 作用的数据源只能是参数类型
    @PostMapping("/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return productService.findList(productIdList);
    }

}
