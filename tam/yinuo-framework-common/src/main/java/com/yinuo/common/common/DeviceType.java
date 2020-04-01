package com.yinuo.common.common;

public enum DeviceType {
	IOS("ios"), PC("pc"), ANDROID("android"),APP("app");

	private final String type;

	public String getType() {
		return type;
	}

	private DeviceType(String type) {
		this.type = type;
	}

}
