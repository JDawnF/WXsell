package com.immoc.utils;

import java.util.Random;

/**
 * @program: sell
 * @description: 索引，约束随机数
 * @author: baichen
 * @create: 2018-08-19 21:48
 **/
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     * 加上synchronized是为了防止多线程的时候数字会重复
     * @return
     */
    public static synchronized String genUniqueKey(){
        Random random=new Random();
        //生成6位随机数
        Integer number=random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
