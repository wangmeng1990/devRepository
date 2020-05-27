package com.ark.hngxt.product.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("设置策略集")
public class UpdateStrategySetVO {

	@ApiModelProperty(value= "产品id",required = true)
	@NotNull(message = "产品id不能为空")
    private Long id;
	
	@ApiModelProperty(value = "贷前审查策略集", required = true)
	@NotBlank(message = "贷前审查策略集不能为空")
	private String creditModel;

	@ApiModelProperty(value = "信用评级策略集", required = true)
	@NotBlank(message = "信用评级策略集不能为空")
	private String ratingModel;
}
