package com.ark.hngxt.product.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="信贷产品")
public class CreditProduct implements java.io.Serializable{
	
	private static final long serialVersionUID = 4331211722780794866L;

	@ApiModelProperty(value = "id", required = false)
	private Long id;

	@ApiModelProperty(value = "产品名称", required = false)
	@NotBlank(message = "产品名称不能为空")
	@Size(max = 50, message = "产品名称不能超过{max}个字符")
	private String name;

	@ApiModelProperty(value = "产品类型", required = false)
	@NotBlank(message = "产品类型不能为空")
	@Size(max = 20, message = "产品类型不能超过{max}个字符")
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

	@ApiModelProperty(value = "参考信用等级", required = false)
	private String creditLevel;
	
	@ApiModelProperty(value = "产品图片", required = false)
	private String img;

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

	@ApiModelProperty(value = "pc首页我要贷款产品置顶", required = false)
	private Integer beTop;

	@ApiModelProperty(value = "产品被申请数", required = false)
    private Long applyCount;
    
	@ApiModelProperty(value = "特定融资资料需要1是0否", required = false)
	private Integer beSpecial;

	@ApiModelProperty(value = "贷前审查策略集", required = false)
	private String creditModel;

	@ApiModelProperty(value = "信用评级策略集", required = false)
	private String ratingModel;

	@ApiModelProperty(value = "产品申请成功率-假数据", required = false)
	private String applyRate;

	@ApiModelProperty(value = "产品累计放款总额：万-假数据", required = false)
    private BigDecimal totalAmount;

	@ApiModelProperty(value = "产品申请成功率-真数据", required = false)
    private String applyRateTrue;

	@ApiModelProperty(value = "产品累计放款总额：万-真数据", required = false)
    private BigDecimal totalAmountTrue;
    
	@ApiModelProperty(value = "状态1启用0暂存", required = true)
	private Integer status;

	@ApiModelProperty(value = "首次发布时间", required = false)
	private Date publishDate;
	
	private Integer sort;//排序
	
	@ApiModelProperty(value = "创建人", required = false)
	private String createBy;

	@ApiModelProperty(value = "创建日期", required = false)
	private Date createDate;

	@ApiModelProperty(value = "修改人", required = false)
	private String updateBy;

	@ApiModelProperty(value = "更新时间", required = false)
	private Date updateDate;
	
	@ApiModelProperty(value = "参考信用等级-字典code", required = false)
	private String creditLevelCode;
}