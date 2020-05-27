package com.ark.hngxt.product.model.statistics;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="产品统计对象" )
@Data
public class Product implements Serializable{

	private static final long serialVersionUID = 8534158561356382269L;

	@ApiModelProperty(value = "产品id",required = false)
	private Long productId;
	
	@ApiModelProperty(value = "发布时间",required = false)
	private Date publishDate;
	
	@ApiModelProperty(value = "产品类型1信用产品2金融产品",required = false)
	private String type;
	
	@ApiModelProperty(value = "融资类型",required = false)
	private String financeType;
	
	@ApiModelProperty(value = "产品名称",required = false)
	private String productName;
	
	@ApiModelProperty(value = "所属机构", required = false)
	private String organization;

	@ApiModelProperty(value = "所属机构id", required = false)
    private Long organizationId; 
	
	@ApiModelProperty(value = "上级所属机构", required = false)
	private String parentOrganization;

	@ApiModelProperty(value = "上级机构id", required = false)
    private Long parentOrganizationId;
	
	@ApiModelProperty(value = "融资额度起", required = false)
	private BigDecimal amountStart;

	@ApiModelProperty(value = "融资额度止", required = false)
	private BigDecimal amountEnd;

	@ApiModelProperty(value = "利率起", required = false)
	private BigDecimal interestRateStart;

	@ApiModelProperty(value = "利率止", required = false)
	private BigDecimal interestRateEnd;

	@ApiModelProperty(value = "融资期限起", required = false)
	private Integer limitStart;

	@ApiModelProperty(value = "融资期限止", required = false)
	private Integer limitEnd;
	
	@ApiModelProperty(value = "数据逻辑状态 1 真 2假", required = false)
	private int logicStatus=1;
	
	@ApiModelProperty(value = "产品被申请数", required = false)
    private Long applyCount;
	
	@ApiModelProperty(value = "产品创建日期", required = false)
	private Date pCreateDate;
	
	@ApiModelProperty(value = "状态1上架0下架3删除", required = false)
	private Integer status;
	
	@ApiModelProperty(value = "产品创建人", required = false)
	private String pCreateBy;
	
	@ApiModelProperty(value = "参考信用等级-字典code", required = false)
	private String creditLevelCode;
	
	@ApiModelProperty(value = "参考信用等级", required = false)
	private String creditLevel;
	
	/**
	 * 以下字段金融产品才有
	 */
	@ApiModelProperty(value = "受理时间", required = false)
	private String acceptanceTime;
	
	@ApiModelProperty(value = "担保方式", required = false)
	private String guaranteeType;
	
	@ApiModelProperty(value = "还款方式", required = false)
	private String payType;
	
	@ApiModelProperty(value = "目标客户", required = false)
	private String targetCustomer;
	
	/**
	 * 可申请区域省
	 */
	private String province;
    
	@ApiModelProperty(value = "可申请区域", required = false)
	private String applyArea;
	
	@ApiModelProperty(value = "产品标签", required = false)
	private String tag;
	
	@ApiModelProperty(value = "担保方式-字典code", required = false)
	private String guaranteeTypeCode;
	
	@ApiModelProperty(value = "可申请区省-字典code", required = false)
	private String provinceCode;
	
	@ApiModelProperty(value = "可申请区域市-字典code", required = false)
	private String applyAreaCode;
	
	@ApiModelProperty(value = "还款方式-字典code", required = false)
	private String payTypeCode;
	
	@ApiModelProperty(value= "融资类型",required = false)
    private String typeCode;

}
