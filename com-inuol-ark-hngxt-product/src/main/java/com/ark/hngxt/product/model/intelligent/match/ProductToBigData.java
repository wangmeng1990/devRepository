package com.ark.hngxt.product.model.intelligent.match;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("推送给大数据的产品信息：用于打产品标签 ")
public class ProductToBigData  implements Serializable{
	
	private static final long serialVersionUID = 7276419190260939440L;

	@ApiModelProperty(value = "产品id", required = false)
	private Long productId;
	
	@ApiModelProperty(value = "trxId：数据交互id", required = false)
	private Long trxId;
	
	@ApiModelProperty(value = "产品名称", required = false)
	private String name;

	@ApiModelProperty(value = "产品类型:1特色产品2金融产品", required = false)
	private String productType;
	
	@ApiModelProperty(value = "所属机构", required = false)
	private String organization;

	@ApiModelProperty(value = "所属机构id", required = false)
    private Long organizationId; 
	
	@ApiModelProperty(value = "上级所属机构", required = false)
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
    
	@ApiModelProperty(value = "状态1启用0暂存", required = false)
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

	@ApiModelProperty(value = "产品介绍", required = false)
	private String description;
	
	@ApiModelProperty(value = "产品特色", required = false)
	private String feature;
	
	@ApiModelProperty(value = "申请条件", required = false)
	private String applyCondition;
	
	@ApiModelProperty(value = "所需资料", required = false)
	private String applyMaterial;
	
	@ApiModelProperty(value = "标签类型：大数据端交互用", required = false)
	private String[] type;
	
	/**金融产品独有字段
	 * */
	@ApiModelProperty(value = "产品融资类型", required = false)
	private String financeType;
	
	@ApiModelProperty(value = "机构类型", required = false)
	private String organizationType;
	
	@ApiModelProperty(value = "受理时间", required = false)
	private String acceptanceTime;
	
	@ApiModelProperty(value = "担保方式", required = false)
	private String guaranteeType;
	
	@ApiModelProperty(value = "还款方式", required = false)
	private String payType;
	
	@ApiModelProperty(value = "目标客户", required = false)
	private String targetCustomer;
	
	@ApiModelProperty(value = "利率是否面议1是0否", required = false)
	private Integer beFace;

	@ApiModelProperty(value = "可申请区域省", required = false)
	private String province;
	    
	@ApiModelProperty(value = "可申请区域", required = false)
	private String applyArea;
	
	@ApiModelProperty(value = "产品标签", required = false)
	private String tag;
	
	@ApiModelProperty(value = "首页金融产品大数据智能推荐1是0否", required = false)
	private Integer beBigDataHot;

	@ApiModelProperty(value = "首页金融产品大数据智能推荐排序", required = false)
    private Integer beBigDataHotSort;
	
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
