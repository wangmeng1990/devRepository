package com.ark.hngxt.product.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("提交意见反馈")
public class AddFeedBackVO {

	@ApiModelProperty(value = "标题", required = true)
	@NotBlank(message = "标题不能为空")
	@Size(max = 100, message = "标题不能超过{max}个字符")
    private String title;

	@ApiModelProperty(value = "类型1政府2金融机构3平台", required = true)
	@NotBlank(message = "类型不能为空")
    private String type;

	@ApiModelProperty(value = "内容", required = false)
	@Size(max = 1000, message = "内容不能超过{max}个字符")
    private String content;

	@ApiModelProperty(value = "附件路径多文件,逗号隔开", required = false)
    private String files;

	@ApiModelProperty(value = "附件名称多文件,逗号隔开", required = false)
    private String filename;
}
