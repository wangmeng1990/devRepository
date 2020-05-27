package com.ark.hngxt.product.model;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("排序")
public class SortVO {
	@ApiModelProperty(value = "排序:1升序2降序", required = true)
	@NotBlank(message = "排序不能为空")
	private String sort;
	@ApiModelProperty(value = "排序字段:1申请量2 周期3利率", required = true)
	@NotBlank(message = "排序字段不能为空")
	private String field;

}
