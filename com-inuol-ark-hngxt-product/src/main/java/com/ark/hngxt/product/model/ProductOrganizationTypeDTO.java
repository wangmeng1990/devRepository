package com.ark.hngxt.product.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("更新金融产品机构类型")
public class ProductOrganizationTypeDTO {
	
	@ApiModelProperty(value = "一级金融机构id", required = true)
	@NotNull(message = "一级金融机构id不能为空")
    private Long orgId;
	
	@ApiModelProperty(value = "机构类型", required = true)
	@NotBlank(message = "机构类型不能为空")
	private String type;
}
