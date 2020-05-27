package com.ark.hngxt.product.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("信贷查询参数对象")
public class QueryCreditProduct {
	
	@ApiModelProperty(value= "产品名称",required = false)
    private String name;
    
	@ApiModelProperty(value = "所属机构id", required = false)
    private Long organizationId;
	
	@ApiModelProperty(value = "上级所属机构id", required = false)
    private Long parentOrganizationId;
	
	@ApiModelProperty(value= "产品状态1发布0下架",required = false)
    private Integer status;
	
	@ApiModelProperty(value = "首贷中心热门推荐1是0否", required = false)
	private Integer beMainHot;

	@ApiModelProperty(value = "大数据征信热门推荐1是0否", required = false)
	private Integer beHot;
	
	@ApiModelProperty(value = "app产品热门推荐1是0否", required = false)
	private Integer beMainHotApp;
	
	
    @ApiModelProperty(value= "页码，默认值0",required = false)
	private Integer pageNum=0;
    
    @ApiModelProperty(value= "每页数据条数，默认值10",required = false)
	private Integer pageSize=10;

	@ApiModelProperty(value= "排序条件",required = false)
    private String orderByClause;
	
	@ApiModelProperty(value= "是否获取某产品被企业申请数",required = false)
    private Boolean getCount=false;
	
	@ApiModelProperty(value= "获取产品被申请率和放宽总额",required = false)
    private Boolean getRateAndAmount=false;
}
