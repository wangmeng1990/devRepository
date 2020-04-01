package com.inuol.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("通用")
public class BaseRequestParam {
	@ApiModelProperty(value = "当前登录用户编号",required = false)
	private String userId;
	@ApiModelProperty(value = "终端：pc，ios，android",required = false)
	private String deviceType;
}
