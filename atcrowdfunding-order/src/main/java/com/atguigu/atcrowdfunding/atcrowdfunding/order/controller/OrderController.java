package com.atguigu.atcrowdfunding.atcrowdfunding.order.controller;

import com.atguigu.atcrowdfunding.front.constant.AppConstant;
import com.atguigu.atcrowdfunding.front.vo.req.OrderCreateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 * @create 2019-06-22 11:00
 */
@Controller
@RequestMapping("/order")
public class OrderController {


    @Autowired
    StringRedisTemplate redisTemplate;

    //创建订单
    @PostMapping("/create")
    public String create(OrderCreateVo vo){

        String jsonString = redisTemplate.opsForValue().get(AppConstant.MEMBER_LOGIN_CHANGE_PREFIX + vo.getAccessToken());



        return null;
    }

}
