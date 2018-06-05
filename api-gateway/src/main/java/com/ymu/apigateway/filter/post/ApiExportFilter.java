package com.ymu.apigateway.filter.post;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.ymu.apigateway.filter.pre.AccessTokenFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiExportFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(AccessTokenFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String resp = ctx.getResponseBody();

        logger.info(String.format("resp content: %s", resp));
        return null;
    }
}
