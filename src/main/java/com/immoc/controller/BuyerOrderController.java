package com.immoc.controller;

import com.immoc.VO.ResultVO;
import com.immoc.converter.OrderForm2OrderDTOConverter;
import com.immoc.dto.OrderDTO;
import com.immoc.enums.OrderStatusEnum;
import com.immoc.enums.ResultEnum;
import com.immoc.exception.SellException;
import com.immoc.form.OrderForm;
import com.immoc.service.BuyerService;
import com.immoc.service.OrderService;
import com.immoc.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @program: sell
 * @description: 买家订单controller
 * @author: baichen
 * @create: 2018-08-19 11:45
 **/

@RestController
@RequestMapping("/buyer/order")
@Slf4j

public class BuyerOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;
    //创建订单,根据api有多个参数，所以新建一个表单验证的类
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){
        //判断表单校验之后有没有错误
        if (bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO=OrderForm2OrderDTOConverter.convert(orderForm);
        //判断购物车是否为空
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String,String> map=new HashMap<>();
        map.put("orderId",createResult.getOrderId());
        return ResultVOUtil.success(map);
    }
    //订单列表,方法的参数都是根据api文档来的
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "size",defaultValue = "10") Integer size){
        // 判断openid是否为空，
        if (StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request=PageRequest.of(page,size);
        Page<OrderDTO> orderDTOPage=orderService.findList(openid,request);
        // 返回一个列表
        return ResultVOUtil.success(orderDTOPage.getContent());
    }
    //订单详情
    @GetMapping("/detail")
    public ResultVO<List<OrderDTO>> detail(@RequestParam("openid") String openid,
                                           @RequestParam("orderId") String orderId){
        //TODO 不安全的做法改进，要用到openid
        // 如果只用orderId的话，那么任何人都可以查
        OrderDTO orderDTO = buyerService.findOrderOne(openid,orderId);
        return ResultVOUtil.success(orderDTO);
    }
    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId){
        //如果只是通过orderId查询订单是不安全的做法改进，要用到openid
        //OrderDTO orderDTO = orderService.findOne(orderId);
        // 直接调用取消订单的方法即可，不用在success方法里返回orderDTO，因为如果不成功会抛异常
        buyerService.cancelOrder(openid,orderId);
        return ResultVOUtil.success();
    }
}
