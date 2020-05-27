package com.ark.hngxt.product.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryOrganizationRecommend {
	
	@ApiModelProperty(value= "企业id",required = false)
	private Long entpId;
	
	@ApiModelProperty(value= "排序",required = false)
	private String orderByClause;
	
	@ApiModelProperty(value= "页码，默认值0",required = false)
	private Integer pageNum=0;
    
    @ApiModelProperty(value= "每页数据条数，默认值10",required = false)
	private Integer pageSize=10;
    
	@ApiModelProperty(value= "机构名称",required = false)
	private String orgName;
    
	@ApiModelProperty(value= "机构类型：商业银行，小贷公司，其他机构",required = false)
	private String orgType;
	
	@ApiModelProperty(value= "是否热门推荐机构1是0否",required = false)
	private String beHot;
	
	@ApiModelProperty(value= "1升序2降序：sortField为3才需要传",required = false)
	private String sort;
	
	@ApiModelProperty(value= "排序字段（1首页列表：按服务次数降序排列;2机构列表-更多：按照放款金额降序排序，若放款金额相同，则按照贷款笔数降序排；若贷款笔数相同，则按照金融机构入驻时间倒序排序;3机构列表-更多：综合服务得分正序、倒序排序",required = false)
	private String sortField;
	
    
}
