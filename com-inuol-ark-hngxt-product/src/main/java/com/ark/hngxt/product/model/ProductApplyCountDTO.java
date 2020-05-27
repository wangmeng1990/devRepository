package com.ark.hngxt.product.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("产品被申请数")
public class ProductApplyCountDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3581623743435005120L;

	@ApiModelProperty(value = "产品类型1特色产品2金融产品", required = true)
	@NotBlank(message = "产品类型不能为空")
	private String type;
	
	@ApiModelProperty(value= "产品id",required = true)
	@NotNull(message = "产品id不能为空")
	private Long productId;
	
	@ApiModelProperty(value= "产品被申请金额",required = false)
	private BigDecimal amount;

}
