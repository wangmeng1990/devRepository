package com.yinuo.common.sms;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;

/**
 * @Title
 */
public class CommonConfiguration {
	private static Properties env = null;
	public static String[] args;

	private static ThreadLocal<Properties> currentThread = new ThreadLocal<Properties>();

	public CommonConfiguration() {
	}

	public CommonConfiguration(String[] args) {
		initEnv(args);
	}

	private void initEnv(String[] args) {
		String profileActive = null;
		for (String arg : args) {
			if (arg.startsWith("--spring.profiles.active=")) {
				profileActive = arg.replace("--spring.profiles.active=", "");
			}
		}
		if (profileActive == null) {
			return;
		}
		YamlPropertiesFactoryBean propertiesBean = new YamlPropertiesFactoryBean();
		propertiesBean.setResources(new ClassPathResource("application.yml"),
				new ClassPathResource("application-" + profileActive + ".yml"));
		Properties p = propertiesBean.getObject();
		if (p != null && !p.isEmpty()) {
			env = p;
			currentThread.set(p);
		}
	}

	public static String getValue(String key) {
		return getValue(key, "");
	}

	/**
	 * 根据Key 获取值
	 *
	 * @param key
	 * @param defaultValue 如果获取值为NULL, 返回defaultValue
	 * @return
	 */
	public static String getValue(String key, String defaultValue) {
		String val = env.getProperty(key);
		if (StringUtils.isBlank(val)) {
			return defaultValue;
		}
		return val;
	}

	/**
	 * 根据Key 获取值
	 *
	 * @param key
	 * @param defaultValue 如果获取值为NULL, 返回defaultValue
	 * @return
	 */
	public static Integer getIntegerValue(String key, Integer defaultValue) {
		String val = env.getProperty(key);
		if (StringUtils.isBlank(val)) {
			return defaultValue;
		}
		return Integer.valueOf(val);
	}
}