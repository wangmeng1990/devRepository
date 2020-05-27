package com.ark.hngxt.product.model;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="金融机构推荐")
public class RecommendOrganizationVO {
	
    @ApiModelProperty(value = "机构名称：对应总行的logo", required = false)
    private String organization;

    @ApiModelProperty(value = "机构id", required = false)
    private Long organizationId;

    @ApiModelProperty(value = "上级机构名称", required = false)
    private String parentOrganization;

    @ApiModelProperty(value = "上级机构id", required = false)
    private Long parentOrganizationId;

    @ApiModelProperty(value = "机构名称", required = false)
    private String name;
    
    @ApiModelProperty(value = "服务范围描述", required = false)
    private String description;

    @ApiModelProperty(value = "机构类型", required = false)
    private String type;

    @ApiModelProperty(value = "机构所在城市", required = false)
    private String city;
    
    @ApiModelProperty(value = "机构logo:小图标", required = false)
    private String logo;

    @ApiModelProperty(value = "贷款笔数", required = false)
    private Integer loanCount;

    @ApiModelProperty(value = "机构联系人", required = false)
    private String connects;
    
    @ApiModelProperty(value = "联系人电话", required = false)
    private String telephone;
    
    @ApiModelProperty(value = "放款金额", required = false)
    private BigDecimal loanAmount;

    @ApiModelProperty(value = "对接企业", required = false)
    private Integer buttCount;
    
    @ApiModelProperty(value = "服务评价次数", required = false)
    private Integer valuationCount;
    
    @ApiModelProperty(value = "产品数量", required = false)
    private String productCount;

    @ApiModelProperty(value = "服务次数", required = false)
    private Integer serviceCount=0;

    @ApiModelProperty(value = "机构综合服务评分", required = false)
    private String serviceScore;
    
    @ApiModelProperty(value = "机构综合服务描述", required = false)
    private String integratedServicesStr;
    
    
    @ApiModelProperty(value = "平均受理时间：天", required = false)
    private String averageAcceptanceTime;

    @ApiModelProperty(value = "利率区间", required = false)
    private String interestRateRang;

    @ApiModelProperty(value = "状态1启用0暂存", required = false)
    private Integer status;

    @ApiModelProperty(value = "创建人", required = false)
    private String createBy;

    @ApiModelProperty(value = "企业入驻时间", required = false)
    private Date createDate;

    @ApiModelProperty(value = "修改人", required = false)
    private String updateBy;

    @ApiModelProperty(value = "修改时间", required = false)
    private Date updateDate;
    
    @ApiModelProperty(value = "服务态度分数", required = false)
    private Integer serviceEvaluationAvg;
    
    @ApiModelProperty(value = "服务态度 高于同类型机构 单位%", required = false)
    private Integer serviceEvaluationHigher;
    
    @ApiModelProperty(value = "专业程度分数", required = false)
    private Integer majorEvaluationAvg;
    
    @ApiModelProperty(value = "专业程度分数高于同类型机构 单位%", required = false)
    private Integer majorEvaluationHigher;
    
    @ApiModelProperty(value = "受理时长分数", required = false)
    private Integer durationEvaluationAvg;
    
    @ApiModelProperty(value = "受理时长高于同类型机构 单位%", required = false)
    private Integer durationEvaluationHigher;
    
    @ApiModelProperty(value = "服务体验图片", required = false)
    private String serviceFile;
    
}