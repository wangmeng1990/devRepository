package com.ark.hngxt.product.domain;

import java.util.Date;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="意见反馈")
public class FeedBack {
	@ApiModelProperty(value = "id", required = false)
    private Long id;

	@ApiModelProperty(value = "标题", required = false)
    private String title;

	@ApiModelProperty(value = "类型1政府2金融机构3平台", required = false)
    private String type;

	@ApiModelProperty(value = "内容", required = false)
    private String content;

	@ApiModelProperty(value = "附件路径多文件,逗号隔开", required = false)
    private String files;

	@ApiModelProperty(value = "附件名称多文件,逗号隔开", required = false)
    private String filename;

	@ApiModelProperty(value = "企业用户: enterprise  机构用户: institutional", required = false)
	private String userType;
	
	@ApiModelProperty(value = "所属机构", required = false)
	private String organization;

	@ApiModelProperty(value = "所属机构id", required = false)
    private Long organizationId; 
	
	@ApiModelProperty(value = "上级所属机构", required = false)
	private String parentOrganization;

	@ApiModelProperty(value = "所属机构id", required = false)
    private Long parentOrganizationId;

	@ApiModelProperty(value = "联系手机号", required = false)
    private String cellPhone;

	@ApiModelProperty(value = "状态1启用0暂存", required = true)
	private Integer status;

	@ApiModelProperty(value = "创建人", required = false)
	private String createBy;

	@ApiModelProperty(value = "创建日期", required = false)
	private Date createDate;

	@ApiModelProperty(value = "修改人", required = false)
	private String updateBy;

	@ApiModelProperty(value = "更新时间", required = false)
	private Date updateDate;
}