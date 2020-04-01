package com.inuol.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PopRequestDto extends BaseRequestDto{
	
	/**
	 * 弹窗名称
	 */
	private String name;
	/**
	 * 弹窗类型
	 */
	private String type;
	
}
