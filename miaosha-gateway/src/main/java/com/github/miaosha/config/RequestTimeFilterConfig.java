package com.github.miaosha.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义请求耗时统计过滤器
 */
@Component
@Slf4j
public class RequestTimeFilterConfig implements GatewayFilter, Ordered {

    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //记录请求开始时间
        exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());

        return chain.filter(exchange).then(Mono.fromRunnable(new Runnable() {
            @Override
            public void run() {
                Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
                if (startTime!=null){
                    log.info(exchange.getRequest().getURI() + " 耗时ms：" + (System.currentTimeMillis() - startTime));
                }
            }
        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
