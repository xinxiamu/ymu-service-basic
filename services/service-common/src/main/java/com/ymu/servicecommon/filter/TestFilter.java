package com.ymu.servicecommon.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

/**
 * 功能简述:<br>
 *     过滤器配置测试。
 *
 * @author zmt
 * @create 2018-04-26 下午5:15
 * @updateTime
 * @since 1.0.0
 */
public class TestFilter implements Filter {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug(">>>>testFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug(">>>>testFilter doFilter");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        logger.debug(">>>>testFilter destroy");
    }
}
