package com.ark.hngxt.product.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("融资类型")
public class FinanceType {
	
	@ApiModelProperty(value= "类型",required = false)
	private String type;
	
	@ApiModelProperty(value= "代码",required = false)
	private String code;

}
