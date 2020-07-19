package com.jinan.springcloud.controller;

import com.jinan.springcloud.entity.Order;
import com.jinan.springcloud.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {


    private static final String str = "http://CLOUD-PAYMENT-SERVICE";


    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/payment/get/{id}")
    public ResponseResult<Order> getOrder(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(str + "/order/list/" + id, ResponseResult.class);
    }

}
