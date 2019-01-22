package com.immoc.service.Impl;

import com.immoc.Repository.ProductInfoRepository;
import com.immoc.dataobject.ProductInfo;
import com.immoc.dto.CartDTO;
import com.immoc.enums.ProductStatusEnum;
import com.immoc.enums.ResultEnum;
import com.immoc.exception.GetException;
import com.immoc.exception.SellException;
import com.immoc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: sell
 * @description:
 * @author: baichen
 * @create: 2018-08-18 21:50
 **/
@Service
//@CacheConfig(cacheNames = "product")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository repository;

    @Override
    //@Cacheable(key = "123")
    public ProductInfo findOne(String productId) {

        return repository.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    //@CachePut(cacheNames = "product",key = "123")
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    //加库存
    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).get();
            //判断商品是否存在
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //增加库存
            Integer number = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(number);
            repository.save(productInfo);
        }
    }

    // 减库存
    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).get();
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
                //throw new GetException();         抛出异常，handler捕获并返回状态码给前端
            }
            Integer number = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (number < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(number);
            repository.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = repository.findById(productId).get();
        if (productId==null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
//        如果商品已经上架，那么就抛出异常
        if (productInfo.getProductStatusEnum()==ProductStatusEnum.UP){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
//         更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return repository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = repository.findById(productId).get();
        if (productId==null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
//        如果商品已经下架，那么就抛出异常
        if (productInfo.getProductStatusEnum()==ProductStatusEnum.Down){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
//         更新
        productInfo.setProductStatus(ProductStatusEnum.Down.getCode());
        return repository.save(productInfo);
    }
}
