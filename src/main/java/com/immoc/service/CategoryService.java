package com.immoc.service;

import com.immoc.dataobject.ProductCategory;

import java.util.List;
//各种接口方法
public interface CategoryService {
    ProductCategory findOne(Integer categoryId);
    List<ProductCategory> findAll();
//    查找商品类目
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    //新增和更新
    ProductCategory save(ProductCategory productCategory);
}
