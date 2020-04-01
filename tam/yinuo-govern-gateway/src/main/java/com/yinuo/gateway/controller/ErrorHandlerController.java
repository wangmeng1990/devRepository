package com.yinuo.gateway.controller;

import com.netflix.zuul.exception.ZuulException;
import com.yinuo.common.common.ErrCodeAndMsg;
import com.yinuo.common.common.HttpResult;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义error错误页面 勿删
 * auth: tfan
 */
@RestController
public class ErrorHandlerController implements ErrorController {
    @RequestMapping("/error")
    public HttpResult error(HttpServletRequest request, HttpServletResponse response){
        String code = request.getAttribute("javax.servlet.error.status_code").toString();
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        String message = "服务器内部错误";
        if (exception instanceof ZuulException) {
            message = exception.getMessage();
        }
        if("429".equals(code)){
            return HttpResult.failure(ErrCodeAndMsg.JUST_MOMENT_ERROR);
        }
        response.setStatus(HttpStatus.OK.value());
        return HttpResult.failure(code,message);
    }
    @Override
    public String getErrorPath() {
        return "/error";
    }
}
