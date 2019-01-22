package com.immoc.Repository;

import com.immoc.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: sell
 * @description: 商品详情dao
 * @author: baichen
 * @create: 2018-08-18 21:15
 **/
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
//    查找商品的状态
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
