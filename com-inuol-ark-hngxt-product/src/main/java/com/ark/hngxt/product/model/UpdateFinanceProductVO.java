package com.ark.hngxt.product.model;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("首贷产品更新")
public class UpdateFinanceProductVO {

	@ApiModelProperty(value= "产品id",required = true)
	@NotNull(message = "产品id不能为空")
    private Long id;
	
	@ApiModelProperty(value= "产品名称",required = true)
    @NotBlank(message="产品名称不能为空")
    @Size(max=50,message="产品名称不能超过{max}个字符")
    private String name;
    
	@ApiModelProperty(value= "融资类型;对应字典为 finance_type，取字典列表返回的name",required = true)
	@NotBlank(message="融资类型不能为空")
	@Size(max=20,message="融资类型不能超过{max}个字符")
	private String type;
    
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
	
    @ApiModelProperty(value= "融资额度起：50",required = true)
    @Digits(integer = 18,fraction = 0)
    @NotNull(message = "起始融资额度不能为空")
    private BigDecimal amountStart;
    
    @ApiModelProperty(value= "融资额度止:900",required = true)
    @Digits(integer = 18,fraction = 0)
    @NotNull(message = "截止融资额度不能为空")
    private BigDecimal amountEnd;
    
    @ApiModelProperty(value= "利率起：5.23",required = false)
    @Digits(integer = 18,fraction = 2)
    private BigDecimal interestRateStart;
    
    @ApiModelProperty(value= "利率止:7.55",required = false)
    @Digits(integer = 18,fraction = 2)
    private BigDecimal interestRateEnd;
    
    @ApiModelProperty(value= "融资期限起:6",required = true)
    @NotNull(message = "起始融资期限不能为空")
    @Range(min = 0,max = 999,message = "请输入3位以内整数")
    private Integer limitStart;
    
    @ApiModelProperty(value= "融资期限止:36",required = true)
    @NotNull(message = "截止融资期限不能为空")
    @Range(min = 0,max = 999,message = "请输入3位以内整数" )
    private Integer limitEnd;
    
	@ApiModelProperty(value = "受理时间:3-5天", required = false)
	@Size(max = 20, message = "受理时间不能超过{max}个字符")
	private String acceptanceTime;
	
	@ApiModelProperty(value = "担保方式;对应字典为 guarantee_type，取字典列表返回的name", required = false)
	@Size(max = 100, message = "担保方式不能超过{max}个字符")
	private String guaranteeType;
	
	@ApiModelProperty(value = "还款方式;对应字典为 pay_type，取字典列表返回的name", required = false)
	@Size(max = 100, message = "还款方式不能超过{max}个字符")
	private String payType;
	
	@ApiModelProperty(value = "目标客户", required = false)
	@Size(max = 50, message = "目标客户不能超过{max}个字符")
	private String targetCustomer;
	
	@ApiModelProperty(value = "利率是否面议1是0否(需要面议则利率区间不需要录入，否则利率区间必录)", required = false)
	@Digits(integer = 1,fraction = 0)
	private Integer beFace;

	@ApiModelProperty(value = "可申请区域省份;对应字典为 province_city_area，取字典列表返回的name", required = true)
	@NotBlank(message="可申请区域省份不能为空")
	@Size(max = 50, message = "可申请区域省份不能超过{max}个字符")
	private String province;
	
	@ApiModelProperty(value = "可申请区域市;对应字典为 选中省份的code，取字典列表返回的name", required = true)
	@NotBlank(message="可申请区域市不能为空")
	@Size(max = 50, message = "可申请区域市不能超过{max}个字符")
	private String applyArea;
	
	@ApiModelProperty(value = "产品标签多条数据之间用 , 逗号隔开", required = false)
	@Size(max = 1000, message = "产品标签不能超过{max}个字符")
	private String tag;
	
	@ApiModelProperty(value = "产品图片", required = false)
	@Size(max = 100, message = "产品图片不能超过{max}个字符")
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

	@ApiModelProperty(value = "产品特色:纯文本，多条信息每一条之间用;分号隔开，段落里面不要使用分号", required = true)
	@Size(max = 1000, message = "产品特色不能超过{max}个字符")
	@NotBlank(message = "产品特色不能为空")
	private String feature;
	
	/**
	 * 排序
	 */
	@ApiModelProperty(value= "排序",required = false)
	@Digits(integer = 4,fraction = 0)
	private Integer sort;
	
	@ApiModelProperty(value = "担保方式-字典code;对应字典为 guarantee_type，取字典列表返回的code", required = false)
	private String guaranteeTypeCode;
	
	@ApiModelProperty(value = "可申请区省-字典code;对应字典为 province_city_area，取字典列表返回的code", required = true)
	@NotBlank(message="可申请区域省份编码不能为空")
	private String provinceCode;
	
	@ApiModelProperty(value = "可申请区域市-字典code;对应字典为 选中省份的code，取字典列表返回的code", required = true)
	@NotBlank(message="可申请区域市编码不能为空")
	private String applyAreaCode;
	
	@ApiModelProperty(value = "还款方式-字典code;对应字典为 pay_type，取字典列表返回的code", required = false)
	private String payTypeCode;
	
	@ApiModelProperty(value= "融资类型;对应字典为 finance_type，取字典列表返回的code",required = true)
	@NotBlank(message="融资类型编码不能为空")
    private String typeCode;
}
