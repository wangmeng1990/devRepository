package com.ark.hngxt.product.model;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("特色产品")
public class CreditProductVO {
	
	@ApiModelProperty(value = "id", required = false)
	private Long id;

	@ApiModelProperty(value = "产品名称", required = false)
	private String name;

	@ApiModelProperty(value = "产品对应的订单类型：1信用产品订单2融资订单", required = false)
	private String orderType;
	
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
	
	@ApiModelProperty(value = "融资额度起", required = false)
	private BigDecimal amountStart;

	@ApiModelProperty(value = "融资额度止", required = false)
	private BigDecimal amountEnd;

	@ApiModelProperty(value= "融资额度：50-100",required = false)
    private String amount;
	
	@ApiModelProperty(value= "融资额度单位：万",required = false)
    private String amountUnit;
	
	@ApiModelProperty(value = "利率起", required = false)
	private BigDecimal interestRateStart;

	@ApiModelProperty(value = "利率止", required = false)
	private BigDecimal interestRateEnd;

	@ApiModelProperty(value= "利率：5.23%-7.23%",required = false)
    private String interestRate;
	
	@ApiModelProperty(value = "融资期限起", required = false)
	private Integer limitStart;

	@ApiModelProperty(value = "融资期限止", required = false)
	private Integer limitEnd;
	
	@ApiModelProperty(value= "融资期限：6-36",required = false)
	private String limit;
	 
	@ApiModelProperty(value = "融资期限单位：月", required = false)
	private String limitUnit;
	
	@ApiModelProperty(value = "参考信用等级", required = false)
	private String creditLevel;
	
	@ApiModelProperty(value = "该产品有多少家企业申请", required = false)
	private Long applyCount=0L;
	
	@ApiModelProperty(value = "产品图片", required = false)
	private String img;

	@ApiModelProperty(value = "贷前审查策略集", required = false)
	private String creditModel;

	@ApiModelProperty(value = "信用评级策略集", required = false)
	private String ratingModel;

	@ApiModelProperty(value = "状态1启用0暂存3删除", required = false)
	private Integer status;
	
	@ApiModelProperty(value = "状态:启用，关闭,删除", required = false)
	private String statusStr;
	
	@ApiModelProperty(value = "产品介绍", required = false)
	private String description;
	
	@ApiModelProperty(value = "产品特色,号隔开", required = false)
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
	
	@ApiModelProperty(value = "首贷中心热门推荐1是0否", required = false)
	private Integer beMainHot;

	@ApiModelProperty(value = "大数据征信热门推荐1是0否", required = false)
	private Integer beHot;
	
	@ApiModelProperty(value = "产品置顶1是0否", required = false)
	private Integer beTop;
	
	@ApiModelProperty(value= "排序",required = false)
	private Integer sort;
	
	@ApiModelProperty(value = "产品申请成功率90%", required = false)
	private String applyRate;
	
	@ApiModelProperty(value = "产品累计放款总额:万", required = false)
	private BigDecimal totalAmount;
	
	@ApiModelProperty(value = "创建人", required = false)
	private String createBy;

	@ApiModelProperty(value = "创建日期", required = false)
	private Date createDate;

	@ApiModelProperty(value = "修改人", required = false)
	private String updateBy;

	@ApiModelProperty(value = "更新时间", required = false)
	private Date updateDate;
	
	@ApiModelProperty(value = "参考信用等级-字典code:对应字典为 credit_level，取字典列表返回的code", required = false)
	private String creditLevelCode;
	
	
	@ApiModelProperty(value = "首页/首贷中心热门推荐排序", required = false)
	private Integer beMainHotSort;

	@ApiModelProperty(value = "app:产品热门推荐1是0否", required = false)
    private Integer beMainHotApp;
	
	@ApiModelProperty(value = "app:产品热门推荐排序", required = false)
    private Integer beMainHotAppSort;
	
	@ApiModelProperty(value = "app:信评热门推荐1是0否", required = false)
    private Integer beHotApp;

	
}
