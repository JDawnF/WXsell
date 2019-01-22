package com.immoc.controller;

import com.immoc.VO.ProductInfoVO;
import com.immoc.VO.ProductVO;
import com.immoc.VO.ResultVO;
import com.immoc.dataobject.ProductCategory;
import com.immoc.dataobject.ProductInfo;
import com.immoc.service.CategoryService;
import com.immoc.service.ProductService;
import com.immoc.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: sell
 * @description: 买家商品
 * @author: baichen
 * @create: 2018-08-18 22:56
 **/
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    // 加入缓存,name想当时Redis的key，第一次访问这个接口会运行以下的代码，
    // 然后返回ResultVO对象，所以这个对象要实现序列化，第二次访问则不会访问以下代码
    // 如果这里不填key的话，参数默认为方法中的参数名
    //@Cacheable(cacheNames = "product",key = "123")
    @Cacheable(cacheNames = "product", key = "#sellerId", condition = "#sellerId.length()>3",
            unless = "#result.getCode()!=0")
//    key可以是动态的，根据sellerId判断存入缓存，用到了sPEL表达式
//    condition表示判断条件,这里只有sellerId大于3才会存入缓存
//    unless判断条件，result是返回的ResultVO，每次condition都会返回一个code
    public ResultVO list(@RequestParam("sellerId") String sellerId) {
        //1. 查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //2. 查询类目(为了提高性能，需要一次性查询)
//        List<Integer> categoryList = new ArrayList<>();
        //传统方法，通过for循环从productInfoList中取出categoryList
//        for (ProductInfo productInfo:productInfoList){
//            categoryList.add(productInfo.getCategoryType());
//        }
        // 精简方法(Java8特性：lambda)
        List<Integer> categoryList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryList);
        //3. 数据拼装,根据api文档，将数据封装为json格式
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
//  避免一个个复制，springboot提供了BeanUtils.copyProperties(Object source,Object target),
// 表示复制source的属性的值到target的属性的值去
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
//        封装了一个接收json格式的静态方法
        return ResultVOUtil.success(productVOList);
    }
}
