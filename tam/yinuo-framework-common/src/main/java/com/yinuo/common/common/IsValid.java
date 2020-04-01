package com.yinuo.common.common;

/**
 * 逻辑删除标志
 * 
 * @author weiss
 *
 */
public enum IsValid {

	/** 有效 */
	VALID(1),
	/** 删除 */
	DELETED(2);

	public final int value;

	private IsValid(int value) {
		this.value = value;
	}
}
