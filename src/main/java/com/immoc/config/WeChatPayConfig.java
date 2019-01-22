package com.immoc.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @program: sell
 * @description: 微信支付配置
 * @author: baichen
 * @create: 2018-08-23 19:31
 **/
@Component
public class WeChatPayConfig {
    /*@Autowired
    private WeChatAccountConfig accountConfig;
    @Bean
    public BestPayServiceImpl bestPayService(){
        BestPayServiceImpl bestPayService=new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config());
        return bestPayService;
    }
    @Bean
    public WxPayH5Config wxPayH5Config(){
        WxPayH5Config wxPayH5Config=new WxPayH5Config();
        wxPayH5Config.setAppId(accountConfig.getMpAppId());
        wxPayH5Config.setAppSecret(accountConfig.getMpAppSecret());
        wxPayH5Config.setMchId(accountConfig.getMchId());
        wxPayH5Config.setMchKey(accountConfig.getMchKey());
        wxPayH5Config.setKeyPath(accountConfig.getKeyPath());
        return wxPayH5Config;
    }*/
}
