package com.guiyang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//该注解用于zookeeper或consul作为注册中心时的注册服务
@EnableDiscoveryClient
public class OrderConsulMain80 {

    public static void main(String[] args){
        SpringApplication.run(OrderConsulMain80.class);
    }

}