package com.yinuo.user.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinuo.common.common.BusinessException;
import com.yinuo.common.common.HttpResult;

/**
 * 统一业务异常处理
 * 
 * @author weiss
 *
 */
@ControllerAdvice
public class BusinessExceptionHandler {

	@ResponseBody
	@ExceptionHandler(BusinessException.class)
	public HttpResult<String> businessException(HttpServletRequest req, BusinessException e) {
		return HttpResult.failure(e.getCode(), e.getMessage());
	}
}
