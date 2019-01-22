package com.immoc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: sell
 * @description: 微信账号相关配置
 * @author: baichen
 * @create: 2018-08-22 21:22
 **/
@Data
@Component
//注入application.yml中的属性，前缀为"wechat"
@ConfigurationProperties(prefix = "wechat")
public class WeChatAccountConfig {
    /**
     * 公众平台id
     */
    private String mpAppId;

    /**
     * 公众平台密钥
     */
    private String mpAppSecret;
    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;
    /**
     * 微信支付异步通知地址
     */
    private String notifyUrl;
}
