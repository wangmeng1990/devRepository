package com.ark.hngxt.product.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="首贷产品")
public class FinanceProduct implements java.io.Serializable{
	
	private static final long serialVersionUID = -669874668634676L;

	@ApiModelProperty(value= "id",required = false)
	private Long id;
    
    @ApiModelProperty(value= "产品名称",required = false)
    @NotBlank(message="产品名称不能为空")
    @Size(max=50,message="产品名称不能超过{max}个字符")
    private String name;
    
    @ApiModelProperty(value= "融资类型",required = false)
    private String type;
    
    @ApiModelProperty(value = "所属机构", required = false)
	@NotBlank(message = "所属机构不能为空")
    private String organization; 
    
    @ApiModelProperty(value = "所属机构id", required = false)
    private Long organizationId; 
    
    @ApiModelProperty(value = "上级所属机构", required = false)
	@NotBlank(message = "上级所属机构不能为空")
	private String parentOrganization;

	@ApiModelProperty(value = "所属机构id", required = false)
    private Long parentOrganizationId;
    
	@ApiModelProperty(value = "机构类型", required = false)
	private String organizationType;
	
	@ApiModelProperty(value = "产品被申请数", required = false)
	private Long applyCount;
	
    @ApiModelProperty(value= "融资额度起",required = false)
    private BigDecimal amountStart;
    
    @ApiModelProperty(value= "融资额度止",required = false)
    private BigDecimal amountEnd;
    
    @ApiModelProperty(value= "利率起",required = false)
    private BigDecimal interestRateStart;
    
    @ApiModelProperty(value= "利率止",required = false)
    private BigDecimal interestRateEnd;
    
    @ApiModelProperty(value= "融资期限起",required = false)
    private Integer limitStart;
    
    @ApiModelProperty(value= "融资期限止",required = false)
    private Integer limitEnd;
    
	@ApiModelProperty(value = "受理时间", required = false)
	private String acceptanceTime;
	
	@ApiModelProperty(value = "担保方式", required = false)
	private String guaranteeType;
	
	@ApiModelProperty(value = "还款方式", required = false)
	private String payType;
	
	@ApiModelProperty(value = "目标客户", required = false)
	private String targetCustomer;
	
	private Integer beFace;//利率是否面议1是0否

	private String province;//可申请区域省
	    
	@ApiModelProperty(value = "可申请区域", required = false)
	private String applyArea;
	
	@ApiModelProperty(value = "产品图片", required = false)
	private String img;
	
	@ApiModelProperty(value = "产品标签", required = false)
	private String tag;
	
	@ApiModelProperty(value = "首页/首贷中心热门推荐1是0否", required = false)
	private Integer beMainHot;

	@ApiModelProperty(value = "大数据征信热门推荐1是0否", required = false)
	private Integer beHot;
	
	@ApiModelProperty(value = "首页/首贷中心热门推荐排序", required = false)
	private Integer beMainHotSort;

	@ApiModelProperty(value = "app:产品热门推荐1是0否", required = false)
    private Integer beMainHotApp;
	
	@ApiModelProperty(value = "app:信评热门推荐1是0否", required = false)
    private Integer beHotApp;

	@ApiModelProperty(value = "app:产品热门推荐排序", required = false)
    private Integer beMainHotAppSort;
	
	@ApiModelProperty(value = "首页金融产品大数据智能推荐1是0否", required = false)
	private Integer beBigDataHot;

	@ApiModelProperty(value = "首页金融产品大数据智能推荐排序", required = false)
    private Integer beBigDataHotSort;
	
	@ApiModelProperty(value = "信用评估模型策略集", required = false)
	private String creditModel;
	
	@ApiModelProperty(value = "评级模型策略集", required = false)
	private String ratingModel;
	
	private Integer sort;//排序
	
	@ApiModelProperty(value = "真产品被申请额", required = false)
	private BigDecimal totalAmountTrue;

	@ApiModelProperty(value = "产品被申请额", required = false)
	private BigDecimal totalAmount;
	    
	@ApiModelProperty(value = "状态1启用0暂存", required = false)
	private Integer status;
	
	@ApiModelProperty(value = "首次发布时间", required = false)
	private Date publishDate;
	
	@ApiModelProperty(value = "创建人", required = false)
	private String createBy;
	
	@ApiModelProperty(value = "创建日期", required = false)
	private Date createDate;
	
	@ApiModelProperty(value = "修改人", required = false)
	private String updateBy;
	
	@ApiModelProperty(value = "更新时间", required = false)
	private Date updateDate;
	
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