package com.ark.hngxt.product.domain;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="金融机构推荐")
public class RecommendOrganization {
	
    private Long id;

    @ApiModelProperty(value = "机构名称", required = false)
    private String organization;

    @ApiModelProperty(value = "机构id", required = false)
    private Long organizationId;

    @ApiModelProperty(value = "上级机构名称", required = false)
    private String parentOrganization;

    @ApiModelProperty(value = "上级机构id", required = false)
    private Long parentOrganizationId;

    @ApiModelProperty(value = "机构名称", required = false)
    private String name;
    
    @ApiModelProperty(value = "机构描述", required = false)
    private String description;

    @ApiModelProperty(value = "机构类型", required = false)
    private String type;

    @ApiModelProperty(value = "机构logo", required = false)
    private String logo;

    @ApiModelProperty(value = "贷款笔数", required = false)
    private Integer loanCount;

    @ApiModelProperty(value = "放款金额", required = false)
    private Long loanAmount;

    @ApiModelProperty(value = "对接企业", required = false)
    private Integer buttCount;
    
    @ApiModelProperty(value = "服务评价次数", required = false)
    private Integer valuationCount;
    
    @ApiModelProperty(value = "产品数量", required = false)
    private Integer productCount;

    @ApiModelProperty(value = "服务次数", required = false)
    private Integer serviceCount;

    @ApiModelProperty(value = "机构综合服务评分", required = false)
    private String serviceScore;
    
    @ApiModelProperty(value = "平均受理时间", required = false)
    private String averageAcceptanceTime;

    @ApiModelProperty(value = "利率区间", required = false)
    private String interestRateRang;

    @ApiModelProperty(value = "状态1启用0暂存", required = false)
    private Integer status;

    @ApiModelProperty(value = "创建人", required = false)
    private String createBy;

    @ApiModelProperty(value = "创建时间", required = false)
    private Date createDate;

    @ApiModelProperty(value = "修改人", required = false)
    private String updateBy;

    @ApiModelProperty(value = "修改时间", required = false)
    private Date updateDate;
}