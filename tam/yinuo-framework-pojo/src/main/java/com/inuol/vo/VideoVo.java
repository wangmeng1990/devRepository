package com.inuol.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("资讯信息")
public class VideoVo extends BaseVo {
	@ApiModelProperty(value = "分类", required = false)
	private String classify;
	@ApiModelProperty(value = "类型", required = false)
	private String type;
	@ApiModelProperty(value = "标题", required = false)
	private String subJect;
	@ApiModelProperty(value = "封面图", required = false)
	private String img;
	@ApiModelProperty(value = "资源链接", required = false)
	private String url;
}
