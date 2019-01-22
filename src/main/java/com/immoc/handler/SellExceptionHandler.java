package com.immoc.handler;

import com.immoc.VO.ResultVO;
import com.immoc.exception.GetException;
import com.immoc.exception.SellException;
import com.immoc.utils.ResultVOUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @program: sell
 * @description: 拦截登录异常,异常统一处理
 * @author: baichen
 * @create: 2018-08-25 22:15
 **/
@ControllerAdvice
public class SellExceptionHandler {
//    处理异常，将错误码返回给前端,相当于捕获抛出的SellException这种异常
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }
//    捕获serviceImpl里面抛出的异常，返回定义好的状态码给前端
//    @ExceptionHandler(value = GetException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public void handlerGetException(){
//
//    }
}
