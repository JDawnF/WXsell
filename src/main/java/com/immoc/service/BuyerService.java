package com.immoc.service;

import com.immoc.dto.OrderDTO;

/*
* 买家service，判断openId是否为同一个*/
public interface BuyerService {
//    查询一个订单
    OrderDTO findOrderOne(String openid,String orderId);
//    取消订单
    OrderDTO cancelOrder(String openid,String orderId);
}
