package cn.lframe.product.service.impl;

import cn.lframe.product.common.DecreaseStockInput;
import cn.lframe.product.common.ProductInfoOutput;
import cn.lframe.product.domain.ProductInfo;
import cn.lframe.product.dto.CartDTO;
import cn.lframe.product.enums.ResultEnum;
import cn.lframe.product.exception.ProductException;
import cn.lframe.product.repository.ProductInfoRepository;
import cn.lframe.product.service.ProductService;
import cn.lframe.product.util.JsonUtil;
import cn.lframe.product.vo.ProductInfoVO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Lframe
 * @create2018 -05 -02 -15:13
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfoVO> findByCategoryType(Integer categoryType) {

        return
                getProductInfoList(categoryType)
                        .stream()
                        .map(this::productInfoToProductInfoVO)
                        .collect(Collectors.toList());
    }

    private List<ProductInfo> getProductInfoList(Integer categoryType) {
        return productInfoRepository.findByCategoryType(categoryType);
    }


    private ProductInfoVO productInfoToProductInfoVO(ProductInfo productInfo) {
        return new ProductInfoVO(productInfo.getProductId(),
                productInfo.getProductName(),
                productInfo.getProductPrice(),
                productInfo.getProductDescription(),
                productInfo.getProductIcon());
    }


    @Override
    public List<ProductInfoVO> findByProductStatusAndCategoryType(Integer productStatus, Integer categoryType) {
        return productInfoRepository.findByProductStatusAndCategoryType(productStatus, categoryType)
                .stream()
                .map(this::productInfoToProductInfoVO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = decreaseStockProcess(decreaseStockInputList);
        List<ProductInfoOutput> productInfoOutputList = productInfoList
                .stream()
                .map(this::productInfoConvertProductInfoOutput)
                .collect(Collectors.toList());
//            发送rabbitmq消息
//            在rabbitMQ中，是我们手动创建的productInfo队列，这里只是发送消息到productInfo，无法自己创建队列。
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputList));

    }

    private ProductInfoOutput productInfoConvertProductInfoOutput(ProductInfo productInfo) {
        ProductInfoOutput productInfoOutput = new ProductInfoOutput();
        BeanUtils.copyProperties(productInfo, productInfoOutput);
        return productInfoOutput;

    }

    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (DecreaseStockInput decreaseStockInput : decreaseStockInputList) {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(decreaseStockInput.getProductId());
            if (!productInfoOptional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_ONT_EXIT);
            }

            ProductInfo productInfo = productInfoOptional.get();
            //库存是否足够
            Integer result = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
            productInfoList.add(productInfo);
        }
        return productInfoList;
    }
}
