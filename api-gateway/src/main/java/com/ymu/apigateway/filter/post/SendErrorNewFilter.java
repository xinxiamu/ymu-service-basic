package com.ymu.apigateway.filter.post;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.ymu.framework.spring.mvc.api.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 处理除了post过滤器抛出的异常。只有抛出异常，才执行该post来处理。以更加友好格式暴露接口给客户端使用。
 * 注：<P>
 * <li>在post类型过滤器抛出异常后，将会被error过滤器处理，但是，不会再被post过滤器处理。</li>
 * <li>在pre、route、post抛出异常都会被error处理，处理后并交给post处理，但是post抛出的异常，error处理后，不再交给post处理</li>
 */
@Component
public class SendErrorNewFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(SendErrorNewFilter.class);

    protected static final String SEND_ERROR_FILTER_RAN = "sendErrorFilter.ran";

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 9;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ZuulFilter failedFilter = (ZuulFilter) ctx.get("failed.filter");
        //上下文中存在遗产，且不是来自post类型过滤器才执行该过滤器
        if(failedFilter != null && !failedFilter.filterType().equals("post") && ctx.containsKey("error.status_code")) {
            return true;
        }
        return false;
    }


    /**
     * 没有按预期输出,上下文中有错误，不会执行{@link org.springframework.cloud.netflix.zuul.filters.post.SendResponseFilter}。
     * 包含错误，会第一个执行{@link org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter}。但是看源码，它把错误重定向到url：“/error”，操
     * @return
     */
    @Override
    public Object run() {
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            Throwable t = ctx.getThrowable();
            logger.info(String.format("api-gateway error: %s \n %s", ((ZuulException) t).errorCause,t.getStackTrace()[1]));

//            ctx.setSendZuulResponse(false); //过滤该请求，不进行路由
            ctx.setResponseStatusCode(200);//设置了其返回的错误码
            ctx.getResponse().setContentType("application/json;charset=UTF-8");

            ApiResult<String> apiResult = new ApiResult<>();
            apiResult.failure(500,"api系统错误，请求失败");
            ctx.setResponseBody(apiResult.toString());


//            HttpServletRequest request = ctx.getRequest();
//
//            HttpServletResponse servletResponse = ctx.getResponse();
//            servletResponse.setCharacterEncoding("UTF-8");
//            OutputStream outStream = servletResponse.getOutputStream();
//            String errormessage = "抱歉，稍后重试";
//            InputStream is = new ByteArrayInputStream(errormessage.getBytes(servletResponse.getCharacterEncoding()));
//            writeResponse(is,outStream);

        } catch (Exception e) {
            ReflectionUtils.rethrowRuntimeException(e);
        }
        return null;
    }

    private void writeResponse(InputStream zin, OutputStream out) throws Exception {
        byte[] bytes = new byte[1024];
        int bytesRead = -1;
        while ((bytesRead = zin.read(bytes)) != -1) {
            out.write(bytes, 0, bytesRead);
        }
    }
}
