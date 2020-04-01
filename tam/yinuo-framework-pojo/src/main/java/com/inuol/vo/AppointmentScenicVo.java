package com.inuol.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("预约入口")
public class AppointmentScenicVo {
	@ApiModelProperty(value = "景点id")
	private String scenicId;
	@ApiModelProperty(value = "景点名称")
	private String scenicName;
	@ApiModelProperty(value = "景点封面")
	private String cover;
	@ApiModelProperty(value = "当前客流")
	private long num;
}
