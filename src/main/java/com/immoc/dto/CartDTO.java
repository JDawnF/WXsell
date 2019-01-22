package com.immoc.dto;

import lombok.Data;

/**
 * @program: sell
 * @description: 购物车传输数据
 * @author: baichen
 * @create: 2018-08-19 21:58
 **/
@Data
public class CartDTO {
    /** 商品Id. */
    private String productId;
    /** 数量. */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
