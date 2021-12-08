package com.xqr.springboot_redis.Controoler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redisTest")
public class Redis_test_Controller {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping
    public String testRedis(){
        //设置值
        redisTemplate.opsForValue().set("name","lucy");
        //获取值
        String name = (String) redisTemplate.opsForValue().get("name");
        return name;

    }
}
