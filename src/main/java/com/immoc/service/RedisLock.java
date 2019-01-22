package com.immoc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @program: sell
 * @description: redis分布式锁，提供加锁和解锁,在秒杀的service中注入调用即可
 * 如加锁：if(!RedisLock.lock(productId,String.valueOF(time))){
 *     throw new SellException(101,"人太多了，你再试试吧");
 * }
 * @author: baichen
 * @create: 2018-08-26 15:30
 **/
@Component
@Slf4j
public class RedisLock {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 加锁
     * 返回的是一个布尔类型，需要判断是否成功
     * @param key,用的是productId
     * @param value            当前时间+超时时间
     * @return
     */
    public boolean lock(String key, String value) {
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }
        String currentValue = redisTemplate.opsForValue().get(key);
        //需要判断锁是否已过期，否则可能会造成死锁的状况
        if (!StringUtils.isEmpty(currentValue) &&
                Long.parseLong(currentValue) < System.currentTimeMillis()) {
            //获取上一个锁的时间,因为被set过了
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
//        判断线程的锁是否一样,即比较前后两个线程的锁是否一样，如果不一样表示只有一个线程拿到锁
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     *
     * @param key
     * @param value
     */
    public void unlock(String key, String value) {
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("【Redis分布式锁】解锁出现异常,{}", e);
        }
    }
}
