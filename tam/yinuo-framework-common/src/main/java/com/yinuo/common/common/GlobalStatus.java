package com.yinuo.common.common;

/**
 * 全局启停状态枚举
 * 
 * @author weiss
 *
 */
public enum GlobalStatus {

	/** 启用 */
	ON(1),

	/** 停用 */
	OFF(2);

	public final int value;

	private GlobalStatus(int value) {
		this.value = value;
	}
	
	public static void checkValidStatus(Integer status) {
		if (status == null) {
			throw new BusinessException("", "状态不能为空");
		}
		
		boolean find = false;
		for(GlobalStatus gs : GlobalStatus.values()) {
			if (status == gs.value) {
				find = true;
				break;
			}
		}
		
		if (find == false) {
			throw new BusinessException("", "无效的状态值");
		}
	}
}
