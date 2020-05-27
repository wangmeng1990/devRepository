package com.ark.hngxt.product.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("产品详情")
public class ProductItemVO {
	
	@ApiModelProperty(value = "产品id", required = false)
	private Long id;
	
	@ApiModelProperty(value = "产品名称", required = false)
	private String name;
	
	@ApiModelProperty(value = "产品类型，用于标记跳转产品详情页，1调特色产品详情2调金融产品详情", required = false)
	private String type;
}
