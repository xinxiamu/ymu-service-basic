package com.ymu.servicefileclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ymu.servicefileclient", "com.ymu.framework"})
@EnableEurekaClient //可注册到服务中心
@EnableDiscoveryClient //可以发现其它服务
@EnableFeignClients //开启Feign方式消费其它服务组件
@EnableCircuitBreaker //开启断路器功能，防止调用多个服务出现雪崩。
public class ServiceFileclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFileclientApplication.class, args);
    }
}
