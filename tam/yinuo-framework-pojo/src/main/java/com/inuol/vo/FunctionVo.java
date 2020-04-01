package com.inuol.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("首页功能区")
public class FunctionVo extends BaseVo{
	    @ApiModelProperty(value = "标志",required = false)
	    private String code;
	    @ApiModelProperty(value = "名称",required = false)
	    private String name;
	    @ApiModelProperty(value = "跳转链接",required = false)
        private String url;
	    @ApiModelProperty(value = "排序",required = false)
	    private Integer sort;
}
