package com.yinuo.common.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 业务异常
 * 
 * @author weiss
 *
 */
@ResponseStatus(value = HttpStatus.OK)
public class BusinessException extends RuntimeException {

	/**
	 * 序列id
	 */
	private static final long serialVersionUID = 1L;

	private String code;
	private String message;

	public BusinessException(String code, String message) {
		super(code + message);
		this.code = code;
		this.message = message;
	}

	public BusinessException(String code, String message, Throwable t) {
		super(code + message, t);
		this.code = code;
		this.message = message;
	}
	public BusinessException(String message) {
		super(message);
		this.code = "error";
		this.message = message;
	}
	public BusinessException(ErrCodeAndMsg error) {
		this.code = error.getErrCode();
		this.message = error.getErrMsg();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
