package com.ymu.apigateway.config;

import com.alibaba.fastjson.JSON;
import com.ymu.framework.spring.mvc.api.ApiResult;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 请求无响应降级返回。请求超时或者服务没启动，将调用，降级返回结果。
 */
@Component
public class CustomFallbackProvider implements FallbackProvider {

    @Override
    public ClientHttpResponse fallbackResponse(Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                ApiResult<String> apiResult = new ApiResult<>();
                apiResult.failure(500,"系统错误，请求失败");
                String jsonStr = JSON.toJSONString(apiResult);
                return new ByteArrayInputStream(jsonStr.getBytes("UTF-8"));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                //和body中的内容编码一致，否则容易乱码
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }
        };
    }

    /**
     * 针对此微服务做回退处理。如果所有服务都做回退，返回“*”或者返回null。
     * @return 返回在注册中心注册的服务name或者服务id
     */
    @Override
    public String getRoute() {
        return null;
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return null;
    }
}
