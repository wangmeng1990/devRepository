package com.ark.hngxt.product.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("热门推荐设置")
public class HotProductVO {

	@ApiModelProperty(value= "产品id",required = true)
	@NotNull(message = "产品id不能为空")
    private Long id;
	
	@ApiModelProperty(value = "pc：首贷中心热门推荐1是0否", required = false)
	private Integer beMainHot;

	@ApiModelProperty(value = "pc：大数据征信热门推荐1是0否", required = false)
	private Integer beHot;
	
	@ApiModelProperty(value = "首页/首贷中心热门推荐排序", required = false)
	@Digits(integer = 3, fraction = 0, message = "请输入3位以内整数")
	private Integer beMainHotSort;

	@ApiModelProperty(value = "app:产品热门推荐1是0否", required = false)
    private Integer beMainHotApp;
	
	@ApiModelProperty(value = "app:信评热门推荐1是0否", required = false)
    private Integer beHotApp;

	@ApiModelProperty(value = "app:产品热门推荐排序", required = false)
	@Digits(integer = 3, fraction = 0, message = "请输入3位以内整数")
    private Integer beMainHotAppSort;
}
