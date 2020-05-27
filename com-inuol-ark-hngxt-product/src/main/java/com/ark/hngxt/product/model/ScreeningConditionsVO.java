package com.ark.hngxt.product.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("金融产品筛选条件")
public class ScreeningConditionsVO {

	@ApiModelProperty(value = "条件类型", required = false)
	private String type;
	
	@ApiModelProperty(value = "条件code", required = false)
	private String code;
	
	@ApiModelProperty(value = "条件列表", required = false)
	private List<ScreeningConditionsItem> list;
}
