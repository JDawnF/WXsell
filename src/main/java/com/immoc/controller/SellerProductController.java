package com.immoc.controller;

import com.immoc.dataobject.ProductCategory;
import com.immoc.dataobject.ProductInfo;
import com.immoc.dto.OrderDTO;
import com.immoc.exception.SellException;
import com.immoc.form.ProductForm;
import com.immoc.service.CategoryService;
import com.immoc.service.OrderService;
import com.immoc.service.ProductService;
import com.immoc.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @program: sell
 * @description: 卖家商品列表
 * @author: baichen
 * @create: 2018-08-24 22:00
 **/
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        //    page默认是从第0页开始的，所以这里要-1
        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
//        注入到模板去
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        try {
            productService.onSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/sell/product/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 保存/更新商品主页，可以被后面进行修改和新增
     *
     * @param productId 非必传
     * @param map
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {
//        如果productId非空，则查出这条商品
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo);
        }
        // 查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("product/index", map);
    }

    /**
     * 保存/更新商品，对前端传过来的表单进行验证
     *
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    // 添加缓存，注意这里的注解要用CachePut，还有name要跟买家端商品列表中一样
    // 这里是保证对商品的更新可以同步到缓存中
    // 每次访问都会执行方法的代码，然后返回结果并存到Redis中去，
    // 但要注意这个注解返回的类型必须跟买家端商品列表接口放回的类型一样
    //@CachePut(cacheNames = "product",key = "123")
//    访问这个方法之后，会清除缓存的内容，第一次进来查数据库，第二次进来查缓存
  //  @CacheEvict(cacheNames = "product",key = "123")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
//        如果有错误，跳转到保存/更新商品主页
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }
        ProductInfo productInfo=new ProductInfo();
        try {
//            如果productId为空，说明是新增商品，此时不需要查询数据库,如果不为空则查询数据库
            if (!StringUtils.isEmpty(form.getProductId())){
                productInfo=productService.findOne(form.getProductId());
            }else {
//                新增商品，要存入id
                form.setProductId(KeyUtil.genUniqueKey());
            }
//        先查询商品，因为有些字段是form表单中没有的，避免拷贝的时候出现null值
            //ProductInfo productInfo=new ProductInfo();
            BeanUtils.copyProperties(form, productInfo);
            productService.save(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }
}
