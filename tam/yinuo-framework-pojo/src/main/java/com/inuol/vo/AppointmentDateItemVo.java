package com.inuol.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("景点的预约时间段")
public class AppointmentDateItemVo {
	@ApiModelProperty(value = "时间段id")
	private long itemId;
	@ApiModelProperty(value = "时间段")
	private String timeQuantum;
	@ApiModelProperty(value = "该时段可预约量")
	private Integer num = 0;// 可预约量
	@ApiModelProperty(value = "该时段已预约量")
	private Integer bookNum = 0;// 已预约量
	@ApiModelProperty(value = "该时段当前是否过期")
	private String expired = "0";// 是否过期0否1是
}
