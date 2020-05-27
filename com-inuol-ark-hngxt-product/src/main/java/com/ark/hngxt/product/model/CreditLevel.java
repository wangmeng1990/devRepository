package com.ark.hngxt.product.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("参考信用评级")
public class CreditLevel {
	
	@ApiModelProperty(value= "等级",required = false)
	private String level;
	
	@ApiModelProperty(value= "代码",required = false)
	private String code;

}
