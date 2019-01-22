package com.immoc.service.Impl;

import com.immoc.dto.OrderDTO;
import com.immoc.service.PayService;
import com.immoc.utils.JsonUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: sell
 * @description:
 * @author: baichen
 * @create: 2018-08-23 19:01
 **/
@Service
@Slf4j
public class PayServiceImpl implements PayService {
    /*private static final String ORDER_NAME = "微信点餐订单";
    @Autowired
    private BestPayServiceImpl bestPayService;

    @Override
    public void create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
//        将BigDecimal类型转换为double类型
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
//      BestPayTypeEnum.WXPAY_H5表示"微信公众账号支付"
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
//        json格式化打印
        log.info("【微信支付】,request={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】,response={}", JsonUtil.toJson(payResponse));
    }*/
}
