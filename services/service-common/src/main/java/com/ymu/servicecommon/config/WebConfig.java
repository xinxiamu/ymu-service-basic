package com.ymu.servicecommon.config;

import com.ymu.framework.spring.config.JsonHandlerExceptionResolver;
import com.ymu.framework.spring.config.JsonHttpMessageConverter2;
import com.ymu.framework.spring.config.JsonViewHttpMessageConverter;
import com.ymu.framework.spring.mvc.api.withhttpheader.CustomRequestMappingHandlerMapping;
import com.ymu.framework.spring.mvc.sensitive.SensitiveFormatAnnotationFormatterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;

/**
 * 采用继承WebMvcConfigurationSupport方式。
 * 这种方式会屏蔽springboot的@EnableAutoConfiguration中的设置
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JsonViewHttpMessageConverter jsonViewHttpMessageConverter;

    /**
     * 配置消息转换规则。
     *
     * @param converters
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //------json与对象转换器
        converters.add(jsonViewHttpMessageConverter);
        converters.add(new JsonHttpMessageConverter2());

        //-----字符串返回转换器
        /*StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        //解决返回乱码的问题。
        stringHttpMessageConverter.setSupportedMediaTypes(MediaType.parseMediaTypes("text/html;charset=UTF-8"));
        converters.add(stringHttpMessageConverter);*/

        //添加其它默认消息转换器
//        super.addDefaultHttpMessageConverters(converters);
    }

    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping handlerMapping = new CustomRequestMappingHandlerMapping();
        handlerMapping.setOrder(0);
        handlerMapping.setInterceptors(getInterceptors());
        return handlerMapping;
    }


    /**
     * 全局验证器
     *
     * @return
     */
    @Override
    protected Validator getValidator() {
//        return new GlobalValidator(); //设置自己的全局表单校验器后，hibernate-valid的将失效
        return super.getValidator();
    }

    @Override
    protected void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldAnnotation(new SensitiveFormatAnnotationFormatterFactory(s -> {
            if ("色情".equals(s)) {
                return "参数中包含敏感词";
            }
            return s;
        }));
        super.addFormatters(registry);
    }

    /**
     * 定义拦截器。
     *
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }

    /**
     * 统一异常处理。
     * @param exceptionResolvers
     */
    @Override
    protected void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new JsonHandlerExceptionResolver());
    }
}
