package com.github.miaosha;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class MiaoShaGatewayApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MiaoShaGatewayApplication.class, args);
        final ConfigurableEnvironment environment = context.getEnvironment();
        final String port = environment.getProperty("server.port");
        final String contextPath = environment.getProperty("server.servlet.context-path");
        log.info("项目启动成功，地址：http://**:"+port + contextPath);
    }

}
