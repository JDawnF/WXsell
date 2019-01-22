package com.immoc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.immoc.dataobject.OrderDetail;
import com.immoc.enums.OrderStatusEnum;
import com.immoc.enums.PayStatusEnum;
import com.immoc.utils.EnumUtil;
import com.immoc.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: sell
 * @description: 数据传输对象，订单和商品详情属性
 * @author: baichen
 * @create: 2018-08-19 21:22
 **/
@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)    这个方法已经过时
//List<OrderDetail> orderDetailList;这个字段在某些接口中不是必须要返回的字段
// 加上@JsonInclude(JsonInclude.Include.NON_NULL)这个注解可以表明使其返回null
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    /** 订单id. */
    private String orderId;

    /** 买家名字. */
    private String buyerName;

    /** 买家手机号. */
    private String buyerPhone;

    /** 买家地址. */
    private String buyerAddress;

    /** 买家微信Openid. */
    private String buyerOpenid;

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单. */
    private Integer orderStatus;

    /** 支付状态, 默认为0未支付. */
    private Integer payStatus;
    // 使用这个注解将日期转换为long类型,如果不加会相差1000，即多3个0
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
    // 附初始值，返回一个[]，前提是不要加入Jackson:default-property-inclusion: non_null
    // 所以只要赋予一个变量初始值，他就会返回空字符(如:[],"")而不是null
//    List<OrderDetail> orderDetailList=new ArrayList<>();
    List<OrderDetail> orderDetailList;
    @JsonInclude    //这个注解表明：对象转成json格式会忽略这个方法
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getBycode(orderStatus,OrderStatusEnum.class);
    }
    @JsonInclude
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getBycode(payStatus,PayStatusEnum.class);
    }
}
