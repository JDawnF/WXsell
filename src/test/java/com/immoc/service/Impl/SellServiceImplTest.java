package com.immoc.service.Impl;

import com.immoc.dataobject.SellerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellServiceImplTest {
    private static final String openid="abc";
    @Autowired
    private SellServiceImpl sellService;

    @Test
    public void findSellInfoByOpenid() {
        SellerInfo result = sellService.findSellInfoByOpenid(openid);
        Assert.assertEquals(openid,result.getOpenid());
    }
}