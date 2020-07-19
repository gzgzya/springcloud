package com.jinan.springcloud.controller;

import com.jinan.springcloud.Iservice.IOrderService;
import com.jinan.springcloud.entity.Order;
import com.jinan.springcloud.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/list/{id}")
    public ResponseResult<Order> list(@PathVariable("id") Integer id) {
        Order order = orderService.getById(id);
        return new ResponseResult<>(1, "获取成功:"+serverPort, order);
    }


}
