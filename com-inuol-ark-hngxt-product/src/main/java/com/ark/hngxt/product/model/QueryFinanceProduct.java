package com.ark.hngxt.product.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("首贷查询参数对象")
public class QueryFinanceProduct {
	
	@ApiModelProperty(value= "产品名称",required = false)
    private String name;
    
	@ApiModelProperty(value= "融资金额：0-2000；20000-",required = false)
    private String amount;
	
	@ApiModelProperty(value= "融资期限：0-12；12-",required = false)
    private String limit;
	
	@ApiModelProperty(value= "机构类型：国有银行,股份制商业银行,保理公司,担保公司,律所",required = false)
    private String organizationType;
	
	@ApiModelProperty(value= "融资类型",required = false)
    private String type;
	
	@ApiModelProperty(value = "担保方式", required = false)
	private String guaranteeType;
	
	@ApiModelProperty(value = "可申请区域", required = false)
	private String applyArea;
	
	@ApiModelProperty(value= "服务机构",required = false)
    private String organization;
	
	@ApiModelProperty(value= "创建时间",required = false)
    private String createDate;
	
	@ApiModelProperty(value= "产品状态1发布0下架",required = false)
    private Integer status;
	 
    @ApiModelProperty(value= "页码，默认值0",required = false)
	private Integer pageNum=0;
    
    @ApiModelProperty(value= "每页数据条数，默认值10",required = false)
	private Integer pageSize=10;

    @ApiModelProperty(value= "排序",required = false)
	public String orderByClause;

	@ApiModelProperty(value = "所属机构id", required = false)
    private String organizationId;
	
	@ApiModelProperty(value = "上级所属机构id", required = false)
    private Long parentOrganizationId;
	
	@ApiModelProperty(value= "是否获取某产品被企业申请数",required = false)
    private Boolean getCount=false;
	
	@ApiModelProperty(value = "首页金融产品大数据智能推荐1是0否", required = false)
	private Integer beBigDataHot;
	
	/**
	 * @see SortVO
	 * */
	@ApiModelProperty(value = "综合排序。jsonArray字符串", required = false)
    private String sort;
}
