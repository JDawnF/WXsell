package com.immoc.dataobject.mapper;

import com.immoc.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {
    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() {
        Map<String,Object> map=new HashMap<>();
        map.put("category_name","师姐最爱");
        map.put("category_type",7);
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1,result);
    }
    @Test
    public void insertByObjectTest(){
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryName("师兄最爱");
        productCategory.setCategoryType(6);
        int result = mapper.insertByObject(productCategory);
        Assert.assertEquals(1,result);
    }
    @Test
    public void findByCategoryTypeTest(){
        ProductCategory result = mapper.findByCategoryType(5);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByCategoryNameTest(){
        List<ProductCategory> result = mapper.findByCategoryName("师姐最爱");
        Assert.assertNotEquals(0,result.size());
    }
    @Test
    public void updateByCategoryTypeTest(){
        int result = mapper.updateByCategoryType("师姐最讨厌的", 6);
        Assert.assertEquals(1,result);
    }
    @Test
    public void updateByObjectTest(){
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryName("师兄最爱");
        productCategory.setCategoryType(5);
        int result = mapper.updateByObject(productCategory);
        Assert.assertEquals(1,result);
    }
    @Test
    public void deleteByCategoryTypeTest(){
        int result = mapper.deleteByCategoryType(5);
        Assert.assertEquals(1,result);
    }
    @Test
    public void selectByCategoryTypeTest(){
        ProductCategory productCategory = mapper.selectByCategoryType(6);
        Assert.assertNotNull(productCategory);
    }
}