package com.guiyang.springcloud.controller;

import com.guiyang.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sun.security.krb5.internal.PAData;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("server.port")
    private String serverPort;


    @GetMapping("/hystrix/payment/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
       return paymentService.paymentInfo_OK(id);
    }



    @GetMapping("/hystrix/payment/error/{id}")

    public String paymentInfo_ERROR(@PathVariable("id") Integer id){
        return paymentService.paymentInfo_ERROR(id);
    }

}
