package com.ymu.servicecommon.config;

import com.ymu.servicecommon.filter.Test2Filter;
import com.ymu.servicecommon.filter.TestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class MainConfig {

    /**
     * 配置过滤器
     * @return
     */
    @Bean
    @Order(Integer.MAX_VALUE -2)
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new TestFilter());
        registration.addUrlPatterns("/*");
//        registration.addInitParameter("abc", "abc-value");
        registration.setName("testFilter");
        return registration;
    }

    @Bean
    @Order(Integer.MAX_VALUE -1)
    public FilterRegistrationBean test2FilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new Test2Filter());
        registration.addUrlPatterns("/*");
//        registration.addInitParameter("abc", "abc-value");
        registration.setName("test2Filter");
        return registration;
    }

}
