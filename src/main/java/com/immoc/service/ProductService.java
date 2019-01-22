package com.immoc.service;
//商品service

import com.immoc.dataobject.ProductInfo;
import com.immoc.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductInfo findOne(String productId);

    //    用户查询上架商品列表
    List<ProductInfo> findUpAll();

    //    管理员查询商品列表
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);
//    加库存,不用返回数据，出错了直接抛异常
    void increaseStock(List<CartDTO> cartDTOList);
//    减库存
    void decreaseStock(List<CartDTO> cartDTOList);
//    商品上架
    ProductInfo onSale(String productId);
//    商品下架
    ProductInfo offSale(String productId);
}
