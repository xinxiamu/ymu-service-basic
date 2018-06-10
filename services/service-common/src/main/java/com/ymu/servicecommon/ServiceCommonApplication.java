package com.ymu.servicecommon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ymu.servicecommon", "com.ymu.framework"})
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceCommonApplication implements CommandLineRunner {

    @Value("${user.dir}")
    private String userDir;

    @Value("${spring.application.name}")
    private String appName;

    public static void main(String[] args) {
        SpringApplication.run(ServiceCommonApplication.class, args);
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
