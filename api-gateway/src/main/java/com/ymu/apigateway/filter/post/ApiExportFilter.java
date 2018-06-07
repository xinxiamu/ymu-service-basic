package com.ymu.apigateway.filter.post;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.ymu.apigateway.filter.pre.AccessTokenFilter;
import com.ymu.framework.spring.mvc.api.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 没有异常。处理服务返回的结果，以更加友好格式返回客户端。
 */
@Component
public class ApiExportFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(ApiExportFilter.class);


    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return !ctx.containsKey("error.status_code"); //api-gateway服务没有发生内部代码异常执行
    }

    @Override
    public Object run() {
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            int a = 9/0;

//            ctx.setSendZuulResponse(false); //过滤该请求，不进行路由
            ctx.setResponseStatusCode(401);//设置了其返回的错误码
            ctx.getResponse().setContentType("application/json;charset=UTF-8");

            ApiResult<String> apiResult = new ApiResult<>();
            apiResult.failure(401,"缺少api验证参数token");
            ctx.setResponseBody(apiResult.toString());

        } catch (Exception e) {
            ReflectionUtils.rethrowRuntimeException(e);
        }
        return null;
    }


}
