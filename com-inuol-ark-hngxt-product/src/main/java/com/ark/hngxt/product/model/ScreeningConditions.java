package com.ark.hngxt.product.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("前端筛选条件")
public class ScreeningConditions {
	
	@ApiModelProperty(value = "机构类型", required = false)
	private String organizationType;
	
	@ApiModelProperty(value = "担保方式", required = false)
	private String guaranteeType;
	
	@ApiModelProperty(value = "融资期限", required = false)
	private String limit;
	
	@ApiModelProperty(value = "融资金额", required = false)
	private String amount;
	
	@ApiModelProperty(value = "申请区域", required = false)
	private String applyArea;
	
	@ApiModelProperty(value = "银行", required = false)
	private String organizationId;
}
