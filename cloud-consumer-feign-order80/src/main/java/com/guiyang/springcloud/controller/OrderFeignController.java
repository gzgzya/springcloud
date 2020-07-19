package com.guiyang.springcloud.controller;

import com.guiyang.springcloud.service.PaymentFeignService;
import com.jinan.springcloud.entity.Order;
import com.jinan.springcloud.utils.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/list/{id}")
    public ResponseResult<Order> getPaymentById(@PathVariable("id") Integer id) {
        return paymentFeignService.list(id);
    }


    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeOut(){
        return paymentFeignService.paymentFeignTimeOut();
    }



}
