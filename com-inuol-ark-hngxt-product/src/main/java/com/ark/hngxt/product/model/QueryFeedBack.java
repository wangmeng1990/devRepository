package com.ark.hngxt.product.model;

import lombok.Data;

@Data
public class QueryFeedBack {
	/**
	 * 标题
	 */
	private String title;
	/**
	 *类型1政府2金融机构3平台 
	 */
	private String type;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 页码，默认值0
	 */
	private Integer pageNum = 0;
	/**
	 * 每页数据条数，默认值10
	 */
	private Integer pageSize = 10;
}
