package com.ark.hngxt.product.model;


import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("意见反馈")
public class FeedBackVO {

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

	@ApiModelProperty(value = "附件路径集合", required = false)
    private List<String> filesList;
	
	@ApiModelProperty(value = "附件名称多文件,逗号隔开", required = false)
    private String filename;
	
	@ApiModelProperty(value = "附件名称集合", required = false)
    private List<String> filenameList;

	@ApiModelProperty(value = "所属机构/所属企业", required = false)
	private String organization;

	@ApiModelProperty(value = "所属机构/所属企业id", required = false)
    private Long organizationId; 
	
	@ApiModelProperty(value = "上级所属机构", required = false)
	private String parentOrganization;

	@ApiModelProperty(value = "所属机构id", required = false)
    private Long parentOrganizationId;

	@ApiModelProperty(value = "联系手机号", required = false)
    private String cellPhone;
}
