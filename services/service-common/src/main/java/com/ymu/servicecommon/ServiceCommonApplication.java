package com.ymu.servicecommon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ymu.servicecommon", "com.ymu.framework"})
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCommonApplication.class, args);
    }
}
