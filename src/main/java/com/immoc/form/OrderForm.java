package com.immoc.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @program: sell
 * @description: 订单表单验证,主要是接受api中json格式的返回数据
 * @author: baichen
 * @create: 2018-08-19 11:51
 **/
@Data
public class OrderForm {
    // 表单验证，买家姓名
    @NotEmpty(message = "姓名必填")
    private String name;
    //买家手机号码
    @NotEmpty(message = "手机号必填")
    private String phone;
    // 买家地址
    @NotEmpty(message = "地址必填")
    private String address;
    //买家微信openid，后期可用于查询订单
    @NotEmpty(message = "openid必填")
    private String openid;
    //购物车
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
