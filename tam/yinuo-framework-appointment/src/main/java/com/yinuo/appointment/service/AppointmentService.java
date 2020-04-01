package com.yinuo.appointment.service;


import java.util.List;

import javax.validation.Valid;

import com.inuol.dto.AppointmentDto;
import com.inuol.dto.CompanionDto;
import com.inuol.dto.CompanionHisDto;
import com.inuol.entity.TouristAppointment;
import com.inuol.entity.TouristCompanion;
import com.inuol.entity.TouristCompanionHis;
import com.inuol.entity.Weather;
import com.inuol.vo.AppointmentDateItemVo;
import com.inuol.vo.AppointmentDateVo;
import com.inuol.vo.AppointmentScenicVo;
import com.inuol.vo.CompanionHisVo;
import com.yinuo.common.common.HttpResult;
import com.yinuo.common.common.ListResult;

public interface AppointmentService {

	HttpResult<ListResult<TouristAppointment>> getAppointmentList(AppointmentDto dto);

	HttpResult<List<TouristCompanion>> getCompanionList(CompanionDto dto);

	HttpResult<List<TouristCompanionHis>> getCompanionHisList(CompanionHisDto dto);

	HttpResult<Object> addCompanion(@Valid CompanionHisVo vo);

	HttpResult<List<AppointmentDateVo>> getAppointment(Integer scenicId,String playDate);

	HttpResult<String> doAppointment(Integer scenicId, long userId, String playDate,
			String companion, Integer itemId, String deviceType);


	HttpResult<AppointmentDateItemVo> getAppointmentItem(long itemId, String playDate);

	HttpResult<Object> cancelAppointment(TouristAppointment ta);

	HttpResult<List<AppointmentScenicVo>> getAppointmentScenic();

	HttpResult<Weather> getWeather();
}
