package com.immoc.dataobject.dao;

import com.immoc.dataobject.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @program: sell
 * @description: dao层注入mapper
 * @author: baichen
 * @create: 2018-08-26 10:50
 **/
public class ProductCategoryDao {
    @Autowired
    private ProductCategoryMapper mapper;
    public int insertByMap(Map<String, Object> map){
        return mapper.insertByMap(map);
    }
}
