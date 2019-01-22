package com.immoc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: sell
 * @description: 微信授权登录，手动获取openid
 * @author: baichen
 * @create: 2018-08-22 18:48
 **/
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeiXinController {
    //微信授权认证登录
    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code) {
        log.info("进入auth方法");
        log.info("code={}", code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx637f88307f56efe7&secret=3a812e201fc4d65d3dfb41328bb8ab9a&code="
                + code + "&grant_type=authorization_code";
//      调用rest服务，面向的是资源而不是面向服务,都是对应HTTP方法
//      这里的getForObject()是发送一个HTTP GET请求，返回的请求体将映射为一个对象
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}", response);
    }
}
