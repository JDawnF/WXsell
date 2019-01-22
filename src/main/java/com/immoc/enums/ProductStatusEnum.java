package com.immoc.enums;

import lombok.Getter;

/**
 * @program: sell
 * @description: 商品状态枚举类
 * @author: baichen
 * @create: 2018-08-18 21:51
 **/
@Getter     // 这个注解相当于getter方法
public enum ProductStatusEnum implements CodeEnum{
    UP(0,"在架"),
    Down(1,"下架")
    ;
    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
