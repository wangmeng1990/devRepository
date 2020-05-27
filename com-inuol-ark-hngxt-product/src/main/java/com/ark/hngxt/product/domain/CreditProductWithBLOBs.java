package com.ark.hngxt.product.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="信贷产品")
public class CreditProductWithBLOBs extends CreditProduct {
	
	private static final long serialVersionUID = -8357878703836505524L;

	@ApiModelProperty(value = "产品介绍", required = false)
	private String description;
	
	@ApiModelProperty(value = "产品特色", required = false)
	private String feature;
	
	@ApiModelProperty(value = "申请条件", required = false)
	private String applyCondition;
	
	@ApiModelProperty(value = "所需资料", required = false)
	private String applyMaterial;
}