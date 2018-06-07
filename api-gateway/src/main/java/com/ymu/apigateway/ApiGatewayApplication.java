package com.ymu.apigateway;

import com.netflix.zuul.FilterProcessor;
import com.ymu.apigateway.config.CustomFilterProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * https://www.jianshu.com/p/ff863d532767
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient //可注册到服务中心
@EnableZuulProxy
public class ApiGatewayApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        FilterProcessor.setProcessor(new CustomFilterProcessor());
    }
}
