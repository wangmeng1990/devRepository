package com.yinuo.common.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * 国际化工具类
 * 
 * @author wangm
 *
 */
//@Component
public class LangUtils {

	private static ResourceBundleMessageSource messageSource;

	@Autowired
	LangUtils(ResourceBundleMessageSource messageSource) {
		LangUtils.messageSource = messageSource;
	}

	public static String toLocale(String msgCode) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(msgCode, null, locale);
	}
}
