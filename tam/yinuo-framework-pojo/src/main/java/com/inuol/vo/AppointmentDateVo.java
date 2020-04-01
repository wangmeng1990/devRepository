package com.inuol.vo;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("预约时间")
public class AppointmentDateVo {
@ApiModelProperty("回传值")
private long dateId;	
@ApiModelProperty("预约日期")
private String playDate;
@ApiModelProperty("当前可预约量")
private Integer remain;//当前可预约量
@ApiModelProperty("预约时间段")
private List<AppointmentDateItemVo>  dateItemVo=new ArrayList<AppointmentDateItemVo>();
}
