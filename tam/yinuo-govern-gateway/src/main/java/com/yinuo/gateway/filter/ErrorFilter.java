package com.yinuo.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.yinuo.common.common.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 *  zuul 过滤器统一异常处理 暂未启用
 *  tfan
 */
/*@Component*/
public class ErrorFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(ErrorFilter.class);

    @Override
    public String filterType() {
        return "error";
    }
    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }



    @Override
    public Object run() throws ZuulException {
        try {
            RequestContext context = RequestContext.getCurrentContext();
            ZuulException exception = this.findZuulException(context.getThrowable());
            logger.error("进入zuul 过滤器异常拦截", exception);
        } catch (Exception var5) {
            ReflectionUtils.rethrowRuntimeException(var5);
        }
        return null;

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

