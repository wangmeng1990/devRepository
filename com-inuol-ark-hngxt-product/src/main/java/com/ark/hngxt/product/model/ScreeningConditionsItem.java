package com.ark.hngxt.product.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("金融产品筛选条件：展示名称name，传到后台的是对应的value")
public class ScreeningConditionsItem {
	
	@ApiModelProperty(value = "名称",required = false)
	private String name;
	
	@ApiModelProperty(value = "对应的值",required = false)
	private String value;
}
