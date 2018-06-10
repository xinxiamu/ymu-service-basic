package com.ymu.servicefileclient;

import com.ymu.framework.core.annotation.ExcludeComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.io.File;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ymu.servicefileclient", "com.ymu.framework"})
@EnableEurekaClient //可注册到服务中心
@EnableDiscoveryClient //可以发现其它服务
@EnableFeignClients //开启Feign方式消费其它服务组件
@EnableCircuitBreaker //开启断路器功能，防止调用多个服务出现雪崩。
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
        value = {ExcludeComponent.class})) //添加了@ExcludeComponent注解的类将不会被ComponentScan扫描
public class ServiceFileclientApplication implements CommandLineRunner {

    @Value("${user.dir}")
    private String userDir;

    @Value("${spring.application.name}")
    private String appName;

    public static void main(String[] args) {
        SpringApplication.run(ServiceFileclientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //创建临时文件夹
        File file = new File(userDir.concat(File.separator).concat("temp").concat(File.separator).concat(appName));
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
