package com.ark.hngxt.product.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("产品查询对象")
public class QueryProductCount {
	
	@ApiModelProperty(value= "查询类型：1信用产品2金融产品3所有",required = false)
    private String type;

	@ApiModelProperty(value = "所属机构id", required = false)
    private Long organizationId;
	
	@ApiModelProperty(value = "上级机构id", required = false)
    private Long parentOrganizationId;
}
