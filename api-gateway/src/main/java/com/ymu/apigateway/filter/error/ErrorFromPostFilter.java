package com.ymu.apigateway.filter.error;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.ymu.framework.spring.mvc.api.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.util.Locale;

/**
 * 仅处理来自post阶段抛出的异常。
 * <P></P>
 * 由于pre阶段，route阶段等的异常最终到会经过post过滤器处理，因此在post处理即可。但是post过滤器
 * 抛出的异常被error过滤器处理后，将不再经过post过滤器处理，因此定义该error过滤器，
 * 但是该过滤器只处理post抛出的异常，其它类型的过滤器异常不处理。
 *
 */
@Component
public class ErrorFromPostFilter extends SendErrorFilter {

    private static Logger logger = LoggerFactory.getLogger(ErrorFromPostFilter.class);

    @Autowired
    private MessageSource messageSource;

    @Override
    public String filterType() {
        return "error";
    }

    /**
     * 返回值要比{@link ErrorFilter}中的大。
     * @return
     */
    @Override
    public int filterOrder() {
        return 30;
    }

    @Override
    public boolean shouldFilter() {
        // 判断：仅处理来自post过滤器引起的异常
        RequestContext ctx = RequestContext.getCurrentContext();
        ZuulFilter failedFilter = (ZuulFilter) ctx.get("failed.filter");
        if(failedFilter != null && failedFilter.filterType().equals("post")) {
            return true;
        }
        return false;
    }

    //处理了，没按预期格式工作
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ZuulException exceptions = this.findZuulException(ctx.getThrowable());
        logger.info(String.format("api服务错误：%s",exceptions.getMessage()));
        if (exceptions != null) {
            try {
                ApiResult<String> apiResult = new ApiResult<>();
                apiResult.failure(500,messageSource.getMessage("error.msg.system",null,Locale.getDefault()));

//                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(500);
                ctx.getResponse().setContentType("application/json;charset=UTF-8");
                ctx.setResponseBody(apiResult.toString());
            } catch (Exception e) {
                logger.error(e.getMessage());
                ReflectionUtils.rethrowRuntimeException(e);
            }
        }

        return null;

       /* try {
            RequestContext context = RequestContext.getCurrentContext();
            ZuulException exception = this.findZuulException(context.getThrowable());
            logger.error("进入系统异常拦截", exception);

            HttpServletResponse response = context.getResponse();
            response.setContentType("application/json; charset=utf8");
            response.setStatus(exception.nStatusCode);
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
                writer.print("{code:"+ exception.nStatusCode +",message:\""+ exception.getMessage() +"\"}");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(writer!=null){
                    writer.close();
                }
            }

        } catch (Exception var5) {
            ReflectionUtils.rethrowRuntimeException(var5);
        }

        return null;*/
    }

    ZuulException findZuulException(Throwable throwable) {
        if (ZuulRuntimeException.class.isInstance(throwable.getCause())) {
            return (ZuulException)throwable.getCause().getCause();
        } else if (ZuulException.class.isInstance(throwable.getCause())) {
            return (ZuulException)throwable.getCause();
        } else {
            return ZuulException.class.isInstance(throwable) ? (ZuulException)throwable : new ZuulException(throwable, 500, (String)null);
        }
    }
}
