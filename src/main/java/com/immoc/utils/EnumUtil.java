package com.immoc.utils;

import com.immoc.enums.CodeEnum;

/**
 * @program: sell
 * @description: 枚举工具类
 * @author: baichen
 * @create: 2018-08-23 21:45
 **/
public class EnumUtil {
//    <T extends CodeEnum>对泛型进行说明
    public static <T extends CodeEnum> T getBycode(Integer code, Class<T> enumClass) {
        for (T each:enumClass.getEnumConstants()){
            if (code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
