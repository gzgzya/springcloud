package com.jinan.springcloud.controller;

import com.jinan.springcloud.Iservice.IOrderService;
import com.jinan.springcloud.entity.Order;
import com.jinan.springcloud.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @Autowired
    DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/list/{id}")
    public ResponseResult<Order> list(@PathVariable("id") Integer id) {
        Order order = orderService.getById(id);
        return new ResponseResult<>(1, "获取成功" + serverPort, order);
    }



    @GetMapping("/discovery")
    public Object getDiscovery(){
        List<String> services = discoveryClient.getServices();
        services.forEach(service->log.info("services:  "+service));
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach(instance->log.error(instance.getServiceId()+"\t"+instance.getHost()
                +"\t"+instance.getPort()+"\t"+instance.getUri()+"\t"+instance.getInstanceId()));
        return this.discoveryClient;
    }

}
