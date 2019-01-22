package com.immoc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: sell
 * @description: 测试日志框架
 * @author: baichen
 * @create: 2018-08-17 23:48
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {
//    private final Logger logger=LoggerFactory.getLogger(LoggerTest.class);
    @Test
    public void test1(){
        String name="immoc";
        String password="123456";
//        logger.debug("debug.....");
//        logger.error("error.....");
//        logger.info("info.....");
        //加上@Slf4j后，不需要再创建一个logger对象即可使用
        log.debug("debug.....");
        log.error("error.....");
        log.info("info.....");
        log.info("name:"+name+",password:"+password);
//        {}表示占位符
        log.info("name:{},password:{}",name,password);
    }
}
