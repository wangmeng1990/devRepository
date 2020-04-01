package com.yinuo.common.utils;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;


public class DataUtils {
	public static boolean isEmpty(Object value) {
		if (null == value || "".equals(value)) {
			return true;
		}
		if (value instanceof Collection) {
			return ((Collection<?>) value).isEmpty();
		}
		if (value instanceof Map) {
			return ((Map<?, ?>) value).isEmpty();
		}
		return false;
	}

	public static boolean isNotEmpty(Object value) {
		return !isEmpty(value);
	}
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
