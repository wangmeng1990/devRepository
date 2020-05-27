package com.ark.hngxt.product.model;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("信贷产品新增")
public class AddCreditProductVO {

	@ApiModelProperty(value = "产品名称", required = true)
	@NotBlank(message = "产品名称不能为空")
	@Size(max = 50, message = "产品名称不能超过{max}个字符")
	private String name;

	@ApiModelProperty(value = "所属金融机构名称", required = false)
	@Size(max = 50, message = "金融机构名称不能超过{max}个字符")
	private String organization;

	@ApiModelProperty(value = "所属金融机构id", required = false)
	private Long organizationId;

	@ApiModelProperty(value = "上级金融机构", required = true)
	@NotBlank(message = "上级金融机构不能为空")
	@Size(max = 50, message = "上级金融机构不能超过{max}个字符")
	private String parentOrganization;
	
	@ApiModelProperty(value = "上级金融机构id", required = true)
	@NotNull(message = "上级金融机构id不能为空")
    private Long parentOrganizationId;
	
	@ApiModelProperty(value = "融资额度起：50", required = true)
	@Digits(integer = 18, fraction = 0)
	@NotNull(message = "起始融资额度不能为空")
	private BigDecimal amountStart;

	@ApiModelProperty(value = "融资额度止:100", required = true)
	@Digits(integer = 18, fraction = 0)
	@NotNull(message = "截止融资额度不能为空")
	private BigDecimal amountEnd;

	@ApiModelProperty(value = "利率起：5.23", required = true)
	@Digits(integer = 18, fraction = 2)
	private BigDecimal interestRateStart;

	@ApiModelProperty(value = "利率止:7.55", required = true)
	@Digits(integer = 18, fraction = 2)
	private BigDecimal interestRateEnd;
	
	@ApiModelProperty(value = "产品置顶1是0否", required = false)
	private Integer beTop;
	
	@ApiModelProperty(value = "融资期限起:6", required = true)
	@NotNull(message = "起始融资期限不能为空")
	@Digits(integer = 3, fraction = 0, message = "请输入3位以内整数")
	private Integer limitStart;

	@ApiModelProperty(value = "融资期限止:36", required = true)
	@NotNull(message = "截止融资期限不能为空")
	@Digits(integer = 3, fraction = 0, message = "请输入3位以内整数")
	private Integer limitEnd;

	@ApiModelProperty(value = "参考信用等级：对应字典为 credit_level，取字典列表返回的name", required = true)
	@NotNull(message = "参考信用等级不能为空")
	private String creditLevel;

	@ApiModelProperty(value = "产品图片", required = true)
	@Size(max = 100, message = "产品图片不能超过{max}个字符")
	@NotBlank(message = "产品图片不能为空")
	private String img;

	@ApiModelProperty(value = "产品介绍：纯文本", required = true)
	@Size(max = 1000, message = "产品介绍不能超过{max}个字符")
	@NotBlank(message = "产品介绍不能为空")
	private String description;

	@ApiModelProperty(value = "申请条件：纯文本，多条信息每一条之间用;分号隔开，段落里面不要使用分号", required = true)
	@Size(max = 1000, message = "申请条件不能超过{max}个字符")
	@NotBlank(message = "申请条件不能为空")
	private String applyCondition;

	@ApiModelProperty(value = "所需资料：纯文本，多条信息每一条之间用;分号隔开，段落里面不要使用分号", required = true)
	@Size(max = 1000, message = "所需资料不能超过{max}个字符")
	@NotBlank(message = "所需资料不能为空")
	private String applyMaterial;

	@ApiModelProperty(value = "产品特色url多条数据之间用,号隔开", required = true)
	@Size(max = 1000, message = "产品特色不能超过{max}个字符")
	@NotBlank(message = "产品特色不能为空")
	private String feature;
	
	/**
	 * sort
	 */
	@ApiModelProperty(value= "排序",required = false)
	@Digits(integer = 4,fraction = 0)
	private Integer sort;
	
	@ApiModelProperty(value = "参考信用等级-字典code:对应字典为 credit_level，取字典列表返回的code", required = true)
	@NotNull(message = "参考信用等级编码不能为空")
	private String creditLevelCode;
}
