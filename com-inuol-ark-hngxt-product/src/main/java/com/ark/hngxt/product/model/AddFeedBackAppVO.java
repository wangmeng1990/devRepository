package com.ark.hngxt.product.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangmeng
 *
 */
@Data
@ApiModel("app提交意见反馈")
public class AddFeedBackAppVO {


	@ApiModelProperty(value = "针对对象1政府2金融机构3平台", required = true)
	@NotBlank(message = "类型不能为空")
    private String type;

	@ApiModelProperty(value = "内容", required = true)
	@NotBlank(message = "内容不能为空")
	@Size(max = 200, message = "内容不能超过{max}个字符")
    private String content;

	@ApiModelProperty(value = "附件路径多文件,逗号隔开", required = false)
    private String files;
}
