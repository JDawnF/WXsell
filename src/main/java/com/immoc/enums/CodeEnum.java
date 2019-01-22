package com.immoc.enums;

/**
 * @program: sell
 * @description: 返回状态
 * @author: baichen
 * @create: 2018-08-23 21:43
 **/
//如果要做成通用的话，应该做成泛型：CodeEnum<T> T getCode()
public interface CodeEnum {
    Integer getCode();
}
