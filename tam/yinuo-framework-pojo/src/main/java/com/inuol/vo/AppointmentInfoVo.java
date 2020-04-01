package com.inuol.vo;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("可预约信息")
public class AppointmentInfoVo {
@ApiModelProperty(value = "时间配置id，回传即可")
private Long dateId; 
@ApiModelProperty(value = "预约时间信息")
private List<AppointmentDateVo> dateVoList=new ArrayList<AppointmentDateVo>();

}
