package com.guiyang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * openFeign 集成了Ribbon，自带负载均衡
 */

@SpringBootApplication
@EnableFeignClients
public class OrderFeignMain80 {

    public static void main(String[] args){
        SpringApplication.run(OrderFeignMain80.class);
    }

}
