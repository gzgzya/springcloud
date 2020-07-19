package com.guiyang.myrule;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义Rule不能再ComponentScan扫描的路径下
 *
 * 负载均衡轮询算法：rest接口第几次请求数%服务器集群总数量=实际调用服务器下标。
 * 每次服务重启后rest接口计数从1开始。
 *
 *
 *
 *
 */
@Configuration
public class MySelfRule {

    //负载均衡：随机
    @Bean
    public IRule getRandomRule(){
        return new RandomRule();
    }

    //轮询
    @Bean
    public IRule getRoundRobinRule(){
        return new RoundRobinRule();
    }

    //先按照RoundRobinRule获取服务，如果服务失败则在指定范围内重试
    @Bean
    public IRule getRetryRule(){
        return new RetryRule();
    }

    //对RoundRobinRule的扩展，优先选择响应速度快的服务
    @Bean
    public IRule getWeightedResponseTimeRule(){
        return new WeightedResponseTimeRule();
    }

    //复合判断server所在区域的性能和可用性选择服务器
    @Bean
    public IRule getZoneAvoidanceRule(){
        return new ZoneAvoidanceRule();
    }

























}
