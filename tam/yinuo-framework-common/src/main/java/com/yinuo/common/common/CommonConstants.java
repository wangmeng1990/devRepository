package com.yinuo.common.common;

/**
 * 常量类
 * 
 * @author OYH
 *
 */
public interface CommonConstants {

	//后台用户Session ID
    String USER_SESSION_ID = "admin_user_session_id";
    
	// 系统默认字符集
	String DEFAULT_CHARACTER = "UTF-8";

	/**
	 * 日期格式
	 */
	String PARTTERN_YYYY_MM_DD = "yyyy-MM-dd";
	String PARTTERN_YYYY_MM_DD_HH = "yyyy-MM-dd HH";
	String PARTTERN_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	String PARTTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	String PARTTERN_YYYYMMDD = "yyyyMMdd";
	String PARTTERN_YYYY_MM = "yyyy-MM";
	String PARTTERN_YYYY_MM_DD_HH_MM_SS_SSS = "yyyyMMddHHmmssSSS";
	String PARTTERN_HH_MM_SS = "HH:mm:ss";

	/**
	 * 导出文件路径 key
	 */
	String INUOL_SERVICE_EXPORT_PATH = "export.path";

	// 短信推送内容前缀
	String SMS_CONTENT_PREFIX = "【壹诺科技】";
	
	/**
	 * 验证通过后 JWT的key字段名
	 */
	public static final String AUTHORIZATION = "Authorization";
	/**
	 * JWT 签名key
	 */
	public static final String SIGNING_KEY = "spring-security-fireeye@Jwt!&Secret^#";
	/**
	 * JWT的前缀标识
	 */
	public static final String INUOL_SQUARE = "InuolSquare ";

	/**
	 * JWT，多字段的分隔符
	 */
	public static final String SPLIT_WAVE = "~";
	/**
	 * 系统默认分隔符
	 */
	public static final String SPLIT_COMMA = ",";
	/**
	 * YML分隔符
	 */
	public static final String SPLIT_MINUS = "-";
	/**
	 * 点 分隔符
	 */
	public static final String SPLIT_POINT = ".";
	/**
	 * 冒号 分隔符
	 */
	public static final String SPLIT_COLON = ":";
	
	/****************************  Redis 中的key 常量****************************************************/
    /**
     * redis中存放用户权限的key值
     */
    public static final String RK_EMPLOYEE_PERMISSION = "employee:permission:%s";
    
    /**
     * 图形验证码redis键值
     */
    public static final String RK_KAPTCHA = "kaptcha:%s";
    
    /**
     * 登录失败redis键值
     */
    public static final String RK_LOGIN_FAIL = "login:fail:%s";
    
    /**
     * 找回密码redis键值
     */
    public static final String RK_SMS_FINDPWD = "sms:findpwd:%s";

	/**
	 *  红包编号 前缀
	 */
	public static final String WELFARE_REDBAGS ="HB";
	/**
	 *  代金卷编号 前缀
	 */
	public static final String WELFARE_VOUCHER ="DJJ";
	/**
	 *  金星翻倍卡编号 前缀
	 */
	public static final String WELFARE_VENUS ="JXFBK";
	/**
	 *  福利套餐编号 前缀
	 */
	public static final String WELFARE_SET ="FLTC";
}
