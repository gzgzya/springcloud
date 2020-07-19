package com.guiyang.springcloud.service;

import com.jinan.springcloud.entity.Order;
import com.jinan.springcloud.utils.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Service
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    /**
     * 这里的方法直接访问"CLOUD-PAYMENT-SERVICE"微服务Controller中的list方法
     */
    @GetMapping("/list/{id}")
    public ResponseResult<Order> list(@PathVariable("id") Integer id);


    /**
     * openFeign的超时控制：openFeign集成了Ribbon,客户端一般默认等待一秒钟
     * @return String
     */
    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeOut();
}
