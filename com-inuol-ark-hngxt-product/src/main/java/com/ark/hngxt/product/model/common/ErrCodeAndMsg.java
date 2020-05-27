package com.ark.hngxt.product.model.common;

/**
 * @Title 接口错误码及错误描述
 * @author wangm
 *
 */
public enum ErrCodeAndMsg {
	/**
	 * 成功
	 */
	SUCCESS("200", null),
	// 重定向
	REDIRECT("301", "redirect"),
	// 资源未找到
	NOT_FOUND("404", "not found"),
	// 服务器错误
	SERVER_ERROR("500","server error"),
	/**
	 * 系统错误
	 */
	FAIL("10001", "系统错误"),
	/**
	 * 权限不足
	 */
	FAIL_AUTHORIZED("10002", "权限不足"),
	/**
	 * 未登錄请登录
	 */
	LOGIN_NEED("10003", "请登录"),
	/**
	 * 暂无记录
	 */
	RECORD_NOT_EXIST("10004", "暂无记录"),
	/**
	 * 请勿频繁操作
	 */
		OPER_RATE_LIMIT("10005", "请勿频繁操作"),
	/**
	 * 请求参数有误
	 */
	REQ_PARAM_ERROR("10006", "请求参数有误"),
	/**
	 * 验证码错误
	 */
	SMSCODE_ERROR("10007", "验证码错误"),
	/**
	 * 账号或密码错误
	 */
	LOGIN_PWD_ERROR("10008", "账号或密码错误"),
	/**
	 * 您的账户已被禁用
	 */
	FREEZE_ACCOUNT("10009", "您的账户已被禁用"),
	/**
	 * 图形验证码错误
	 */
	VERIFICATION_CODE_INVALID("10010", "图形验证码错误"),
	/**
	 * 您已经连续输错三次密码，请尝试通过忘记密码找回
	 */
	LOGIN_PWD_ERROR_THREE("10013", "您已经连续输错三次密码，请尝试通过忘记密码找回"),
	/**
	 * 您已经连续输错三次密码，请联系管理员重置
	 */
	LOGIN_PWD_ERROR_THREE_PC("10022", "您已经连续输错三次密码，请联系管理员重置"),
	/**
	 * 您的账号已经在其他设备上登录
	 */
	LOGIN_ERROR_OUT("10023", "您的账号已经在其他设备上登录"),
	/**
	 * 部门名称重复
	 */
	DEPARTMENT_NAME_ERROR("10024", "部门名称重复"),
	/**
	 * 部门下包含员工，无法删除
	 */
	DEPARTMENT_DELETE_ERROR("10025", "部门下包含员工，无法删除"),
	/**
	 * 员工手机号重复
	 */
	EMPLOYEE_MOBILE_ERROR("10026", "手机号已存在"),
	/**
	 * 修改密码旧密码错误
	 */
	UPDATE_PASSWORD_ERROR("10027", "旧密码错误"),
	/**
	 * 请稍侯再试
	 */
	JUST_MOMENT_ERROR("602","请稍侯再试");

	;

	/**
	 * 错误码
	 */
	private String errCode;
	/**
	 * 错误描述
	 */
	private String errMsg;

	ErrCodeAndMsg(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
			return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
