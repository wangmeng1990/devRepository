package com.ark.hngxt.product.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("产品大类")
public class ProductVO {
	
	@ApiModelProperty(value = "产品大类", required = false)
	private String name;
	
	@ApiModelProperty(value = "产品大类描述", required = false)
	private String description;
	
	@ApiModelProperty(value = "产品列表", required = false)
	private List<ProductItemVO> productList;
}
