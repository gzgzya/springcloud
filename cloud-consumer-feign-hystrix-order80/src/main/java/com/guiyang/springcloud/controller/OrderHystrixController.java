package com.guiyang.springcloud.controller;

import com.guiyang.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * 如果明确有@HystrixCommand(已解释)注解的方法则优先使用@HystrixCommand注解的方法，
 * 没有则使用DefaultProperties()+@HystrixCommand(未解释)的方法
 * 若无@HystrixCommand()的方法则不进行HyStrix处理
 */
@RestController
@DefaultProperties(defaultFallback = "paymentInfo_GLOBAL_HANDLER")
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;


    @GetMapping("/hystrix/payment/ok/{id}")
    @HystrixCommand
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_OK(id);
    }


    @HystrixCommand(fallbackMethod = "paymentInfo_ERROR_HANDLER",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliSecond",value = "3000")
    })
    @GetMapping("/hystrix/payment/error/{id}")
    public String paymentInfo_ERROR(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_ERROR(id);
    }

    public String paymentInfo_ERROR_HANDLER(@PathVariable("id") Integer id) {
        return "请联系管理员";
    }



    //全局的fallbackMethod不能有参数，否则会报找不到
    public String paymentInfo_GLOBAL_HANDLER(@PathVariable() Integer id) {
        return "请联系管理员";
    }

}
