//package com.ymu.servicefileclient.config;
//
//import com.ymu.framework.core.annotation.ExcludeComponent;
//import feign.Contract;
//import feign.auth.BasicAuthRequestInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 默认的是FeignClientsConfiguration。配置这里将覆盖默认的。
// */
//@Configuration
//@ExcludeComponent
//public class FeignConfiguration {
//
//    /**
//     * 覆盖默认的。springMVC方式。
//     * @return
//     */
//    @Bean
//    public Contract feignContract() {
//        return new feign.Contract.Default();
//    }
//
//    /**
//     * 需要请求认证。
//     * @return
//     */
//    @Bean
//    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
//        return new BasicAuthRequestInterceptor("user", "password");
//    }
//}
