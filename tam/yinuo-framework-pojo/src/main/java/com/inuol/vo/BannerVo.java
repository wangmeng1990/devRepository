package com.inuol.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("banner")
public class BannerVo extends BaseVo{
	    @ApiModelProperty(value = "标志",required = false)
	    private String code;
	    @ApiModelProperty(value = "主题",required = false)
	    private String subJect;
	    @ApiModelProperty(value = "类型",required = false)
	    private String type;
	    @ApiModelProperty(value = "跳转链接",required = false)
        private String url;
	    @ApiModelProperty(value = "图片",required = false)
        private String img;
}
