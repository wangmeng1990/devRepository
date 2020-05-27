package com.ark.hngxt.product.model;

import java.math.BigDecimal;
import java.util.List;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("产品智能匹配列表")
public class ProductIntelligentMatchVO {
	
	//金融产品
	@ApiModelProperty(value = "产品id", required = false)
	private Long id;
	
    @ApiModelProperty(value = "产品对应的订单类型：1信用产品订单2融资订单", required = false)
	private String orderType;
    
    @ApiModelProperty(value= "产品名称",required = false)
    private String name;
    
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
    
	@ApiModelProperty(value = "产品图片", required = false)
	private String img;
	
	@ApiModelProperty(value = "该产品有多少家企业申请", required = false)
	private Long applyCount=0L;
	
	@ApiModelProperty(value = "产品标签：多个图片地址,逗号隔开", required = false)
	private String tag;
	
	@ApiModelProperty(value = "产品标签：一条一条展示", required = false)
	private List<String> tagList;
	
	@ApiModelProperty(value = "产品介绍", required = false)
	private String description;
	
	@ApiModelProperty(value = "产品特色", required = false)
	private String feature;
	
	@ApiModelProperty(value = "产品特色:一条一条的展示", required = false)
	private List<String> featureList;
	
	@ApiModelProperty(value = "申请条件:一条一条的展示", required = false)
	private List<String> applyConditionList;
	
	@ApiModelProperty(value = "所需资料:一条一条的展示", required = false)
	private List<String> applyMaterialList;
}
