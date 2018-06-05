package com.ymu.apigateway;

import com.ymu.apigateway.filter.post.ApiExportFilter;
import com.ymu.apigateway.filter.pre.AccessTokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient //可注册到服务中心
@EnableZuulProxy
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    /**
     * 让过滤器生效。
     * @return
     */
    @Bean
    public AccessTokenFilter accessTokenFilter() {
        return new AccessTokenFilter();
    }

    @Bean
    public ApiExportFilter apiExportFilter() {
        return new ApiExportFilter();
    }

}
