package com.ark.hngxt.product.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="首贷产品")
public class FinanceProductWithBLOBs extends FinanceProduct {
	
	private static final long serialVersionUID = 3982969621324587383L;

	@ApiModelProperty(value = "产品介绍", required = false)
	private String description;
	
	@ApiModelProperty(value = "产品特色", required = false)
	private String feature;
	
	@ApiModelProperty(value = "申请条件", required = false)
	private String applyCondition;
	
	@ApiModelProperty(value = "所需资料", required = false)
	private String applyMaterial;
}