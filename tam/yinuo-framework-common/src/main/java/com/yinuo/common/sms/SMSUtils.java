package com.yinuo.common.sms;

import com.alibaba.fastjson.JSONObject;
import com.yinuo.common.common.CommonConstants;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Title 亿美新版短信工具类
 */
public class SMSUtils {
	private static final Logger logger = LoggerFactory.getLogger(SMSUtils.class);
	public static final String SMSswitch = CommonConfiguration.getValue("Emay.switch");
	private static final String APP_ID = CommonConfiguration.getValue("Emay.appId");
	private static final String SECRET_KEY = CommonConfiguration.getValue("Emay.secretKey");
	private static final String SEND_URL = CommonConfiguration.getValue("Emay.url", "shmtn.b2m.cn/simpleinter/sendSMS");
	private static final String CONTENT_TYPE = "application/x-www-form-urlencoded";

	public static int sendSMS(String[] mobiles, String smsContent) {
		if (!SMSswitch.equals("true")) {
			return 0;
		}
		JSONObject params = new JSONObject();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mobiles.length; i++) {
			sb.append(mobiles[i]);
			if (i != (mobiles.length - 1)) {
				sb.append(",");
			}
		}
		params.put("mobiles", sb.toString());
		params.put("content", smsContent);
		params.put("appId", APP_ID);

		String sign;
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		try {
			sign = DigestUtils.md5(APP_ID + SECRET_KEY + timestamp);
		} catch (Exception e) {
			logger.warn("生成亿美签名时发生异常", e.getMessage());
			return -1;
		}
		params.put("sign", sign);
		params.put("timestamp", timestamp);
		String urlParameter = "";
		for (String key : params.keySet()) {
			urlParameter = urlParameter + "&" + key + "=" + params.get(key);
		}
		urlParameter = urlParameter.substring(1, urlParameter.length());
		try {
			String resultStr = HttpClientUtils.postGeneralUrl(SEND_URL, CONTENT_TYPE, urlParameter,
					CommonConstants.DEFAULT_CHARACTER);
			JSONObject result;
			if (StringUtils.isNotBlank(resultStr)) {
				result = JSONObject.parseObject(resultStr);
				logger.info("亿美短信发送结果：{}", resultStr);
				if (result != null && "SUCCESS".equals(result.get("code"))) {
					return 0;
				}
			}
		} catch (Exception e) {
			logger.warn("发送亿美短信发生异常：{}", e.getMessage());
		}
		return -1;
	}
}
