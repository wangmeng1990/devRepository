package com.ark.hngxt.product.model;

import lombok.Data;

@Data
public class ProductCommonVO {
	/**
	 * 产品id
	 */
	private Long id;
	/**
	 * 1特色产品
	 */
	private String type;
	/**
	 * 所属金融机构id
	 */
	private Long organizationId;
	/**
	 * 上级金融机构id
	 */
	private Long parentOrganizationId;
}
