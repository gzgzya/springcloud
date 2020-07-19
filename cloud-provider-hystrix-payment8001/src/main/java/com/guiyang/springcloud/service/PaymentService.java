package com.guiyang.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PaymentService {

    //正常访问
    public String paymentInfo_OK(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + "paymentInfo_OK";
    }


    /**
     * 方法出现异常
     * 一旦调用服务方法失败并抛出错误信息之后、
     * 会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
     * <p>
     * 3000毫秒以内走正常的业务逻辑、3000毫秒之外执行fallbackMethod方法
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_ERROR_HANDLER", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_ERROR(Integer id) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + "paymentInfo_ERROR";
    }

    /**
     * paymentInfo_ERROR发生异常、超时都会跳转到paymentInfo_ERROR_HANDLER方法进行处理
     */
    public String paymentInfo_ERROR_HANDLER(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + "paymentInfo_ERROR_HANDLER";

    }


    //=====================服务熔断

    /**
     * 对应服务雪崩效应的一种微服务链路保护机制，当扇出的某个微服务出错不可用或
     * 响应时间长时，会进行服务降级进而熔断该节点微服务的调用，
     * 当检测到该节点微服务调用响应正常后恢复调用链路
     */

    @HystrixCommand(fallbackMethod = "paymentCricuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreak",value = "true"),   //是否开启断路器
            @HystrixProperty(name = "circuitBreak.requestVolumeThreshold",value = "10"),  //请求次数
            //时间窗口期。在10000毫秒内，如果hystrix命令的调用不足10次，即便所有的请求都超时或者其他原因，断路器都不会打开。
            @HystrixProperty(name = "circuitBreak.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreak.errorThresholdPercentage",value = "60")   //失败率达到多少后跳匝
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("id 不可用");
        }

        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号" + serialNumber;
    }


    public String paymentCricuitBreaker_fallback(@PathVariable() Integer id) {
        return "id 不能负数，请稍后再试" + id;
    }


}

