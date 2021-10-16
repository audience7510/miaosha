package com.github.miaosha.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class GatewayConfig {
    /**
     *
     * @param builder
     * @return
     * @description 将自定义过滤器，注册到路由中
     */
//    @Bean
//    public RouteLocator getRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes().
//                route(new Function<PredicateSpec, Route.AsyncBuilder>() {
//                    @Override
//                    public Route.AsyncBuilder apply(PredicateSpec predicateSpec) {
//                        return predicateSpec
//                                .path("/admin/**")
//                                .filters(gatewayFilterSpec -> gatewayFilterSpec.filter(new RequestTimeFilterConfig()))
//                                .uri("lb://miaosha-admin")
//                                .id("requestTimeFilter)");
//
//                    }
//                }).build();
//    }
}
