package com.inuol.dto;


import lombok.Data;

@Data
public class BaseRequestDto {
	private Long id;
	/**
	 * 当前登录用户id
	 */
	private Long userId;
	/**
	 * 终端：pc，ios，android
	 */
	private String deviceType;
	/**
	 * 页码
	 */
	private Integer pageNum;
	/**
	 * 每页数据条数
	 */
	private Integer pageSize;
	/**
	 * 状态：1启用0未启用3删除
	 */
	private int state;
	/**
	 * 是否管理端接口：true or false
	 */
	private boolean beManage=false;
}
