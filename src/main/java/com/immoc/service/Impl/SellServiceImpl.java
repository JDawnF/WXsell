package com.immoc.service.Impl;

import com.immoc.Repository.SellerInfoRepository;
import com.immoc.dataobject.SellerInfo;
import com.immoc.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: sell
 * @description:
 * @author: baichen
 * @create: 2018-08-25 20:40
 **/
@Service
public class SellServiceImpl implements SellService {
    @Autowired
    private SellerInfoRepository repository;
    @Override
    public SellerInfo findSellInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
