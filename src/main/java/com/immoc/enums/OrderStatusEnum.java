package com.immoc.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum implements CodeEnum  {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),
    ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
//    遍历code，返回订单状态,这种代码太死板，可以写成枚举工具类
//    public static OrderStatusEnum getOrderStatusEnum(Integer code) {
//        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
//            if (orderStatusEnum.getCode().equals(code)) {
//                return orderStatusEnum;
//            }
//        }
//        return null;
//    }
}
