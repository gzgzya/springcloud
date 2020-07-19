package com.duyun.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {


    /**
     * 当访问地址http://localhost:9527/guonei时会自动转发到地址：http://news.baidu.com/guonei
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_duyun", r -> r.path("/guonei")
                .uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }


    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routors = routeLocatorBuilder.routes();
        routors.route("path_route_duyun2", r -> r.path("/guoji")
                .uri("http://news.baidu.com.guoji")).build();
        return routors.build();
    }


}
