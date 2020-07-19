package com.jinan.springcloud.Iservice.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinan.springcloud.Iservice.IOrderService;
import com.jinan.springcloud.entity.Order;
import com.jinan.springcloud.mapper.OrderMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {



}
