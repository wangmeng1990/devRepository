package com.ark.hngxt.product.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("金融产品")
public class FinanceProductVO {
	
	@ApiModelProperty(value= "id",required = false)
	private Long id;
    
    @ApiModelProperty(value= "产品名称",required = false)
    private String name;
    
    @ApiModelProperty(value = "产品对应的订单类型：1信用产品订单2融资订单", required = false)
	private String orderType;
    
    @ApiModelProperty(value= "融资类型",required = false)
    private String type;
    
    @ApiModelProperty(value = "所属机构", required = false)
    private String organization; 
    
    @ApiModelProperty(value = "所属机构id", required = false)
    private Long organizationId; 
    
    @ApiModelProperty(value = "机构logo", required = false)
	private String organizationLogo;
    
    @ApiModelProperty(value = "机构小logo", required = false)
	private String logo;
    
    @ApiModelProperty(value = "上级所属机构", required = false)
	private String parentOrganization;

	@ApiModelProperty(value = "所属机构id", required = false)
    private Long parentOrganizationId;
    
    @ApiModelProperty(value= "融资额度起",required = false)
    private BigDecimal amountStart;
    
    @ApiModelProperty(value= "融资额度止",required = false)
    private BigDecimal amountEnd;
    
    @ApiModelProperty(value= "融资额度：50-100",required = false)
    private String amount;
    
    @ApiModelProperty(value= "融资额度单位：万",required = false)
    private String amountUnit;
    
    @ApiModelProperty(value= "利率起",required = false)
    private BigDecimal interestRateStart;
    
    @ApiModelProperty(value= "利率止",required = false)
    private BigDecimal interestRateEnd;
    
    @ApiModelProperty(value= "利率：5.23%-7.23%",required = false)
    private String interestRate;
    
    @ApiModelProperty(value= "融资期限起",required = false)
    private Integer limitStart;
    
    @ApiModelProperty(value= "融资期限止",required = false)
    private Integer limitEnd;
    
    @ApiModelProperty(value= "融资期限：6-36",required = false)
    private String limit;
    
    @ApiModelProperty(value= "融资期限单位：月",required = false)
    private String limitUnit;
    
	@ApiModelProperty(value = "受理时间", required = false)
	private String acceptanceTime;
	
	@ApiModelProperty(value = "担保方式", required = false)
	private String guaranteeType;
	
	@ApiModelProperty(value = "还款方式", required = false)
	private String payType;
	
	@ApiModelProperty(value = "目标客户", required = false)
	private String targetCustomer;
	
	@ApiModelProperty(value = "利率是否面议1是0否(需要面议则利率区间不需要录入，否则利率区间必录)", required = false)
	private Integer beFace;

	@ApiModelProperty(value = "可申请区域省份", required = false)
	private String province;
	
	@ApiModelProperty(value = "可申请区域市", required = false)
	private String applyArea;
	
	@ApiModelProperty(value = "产品图片", required = false)
	private String img;
	
	@ApiModelProperty(value = "该产品有多少家企业申请", required = false)
	private Long applyCount=0L;
	
	@ApiModelProperty(value = "产品标签：多个图片地址,逗号隔开", required = false)
	private String tag;
	
	@ApiModelProperty(value = "产品标签：一条一条展示", required = false)
	private List<String> tagList;
	
	@ApiModelProperty(value = "信用评估模型策略集", required = false)
	private String creditModel;
	
	@ApiModelProperty(value = "评级模型策略集", required = false)
	private String ratingModel;
	
	@ApiModelProperty(value = "状态1启用0暂存3删除", required = false)
	private Integer status;
	
	@ApiModelProperty(value = "状态:启用，关闭,删除", required = false)
	private String statusStr;
	
	@ApiModelProperty(value = "产品介绍", required = false)
	private String description;
	
	@ApiModelProperty(value = "产品特色", required = false)
	private String feature;
	
	@ApiModelProperty(value = "申请条件", required = false)
	private String applyCondition;
	
	@ApiModelProperty(value = "所需资料", required = false)
	private String applyMaterial;
	
	@ApiModelProperty(value = "产品特色:一条一条的展示", required = false)
	private List<String> featureList;
	
	@ApiModelProperty(value = "申请条件:一条一条的展示", required = false)
	private List<String> applyConditionList;
	
	@ApiModelProperty(value = "所需资料:一条一条的展示", required = false)
	private List<String> applyMaterialList;
	
	@ApiModelProperty(value= "排序",required = false)
	private Integer sort;
	
	@ApiModelProperty(value = "创建人", required = false)
	private String createBy;
	
	@ApiModelProperty(value = "创建日期", required = false)
	private Date createDate;
	
	@ApiModelProperty(value = "修改人", required = false)
	private String updateBy;
	
	@ApiModelProperty(value = "更新时间", required = false)
	private Date updateDate;
	
	@ApiModelProperty(value = "担保方式-字典code;对应字典为 guarantee_type，取字典列表返回的code", required = false)
	private String guaranteeTypeCode;
	
	@ApiModelProperty(value = "可申请区省-字典code;对应字典为 province_city_area，取字典列表返回的code", required = false)
	@NotBlank(message="可申请区域省份编码不能为空")
	private String provinceCode;
	
	@ApiModelProperty(value = "可申请区域市-字典code;对应字典为 选中省份的code，取字典列表返回的code", required = false)
	@NotBlank(message="可申请区域市编码不能为空")
	private String applyAreaCode;
	
	@ApiModelProperty(value = "还款方式-字典code;对应字典为 pay_type，取字典列表返回的code", required = false)
	private String payTypeCode;
	
	@ApiModelProperty(value= "融资类型;对应字典为 finance_type，取字典列表返回的code",required = false)
	@NotBlank(message="融资类型编码不能为空")
    private String typeCode;
}
