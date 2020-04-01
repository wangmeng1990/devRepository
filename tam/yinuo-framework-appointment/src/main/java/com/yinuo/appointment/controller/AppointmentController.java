package com.yinuo.appointment.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.yinuo.appointment.service.AppointmentService;
import com.yinuo.common.common.HttpResult;
import com.yinuo.common.common.ListResult;
import com.yinuo.common.utils.DataUtils;
import com.yinuo.common.web.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value ="/appointmentController" )
@Api(tags="预约管理")
public class AppointmentController extends BaseController{
	@Autowired
	private AppointmentService appointmentService;
	
	@ApiOperation(value = "用户查看预约信息")
	@RequestMapping(value = "/getAppointmentList", method = RequestMethod.POST)
	public HttpResult<ListResult<TouristAppointment>> getAppointmentList(
			@ApiParam(name = "status",value = "预约状态:0待游玩1已游玩2已取消",required = false) @RequestParam(required = false) String status,
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false,defaultValue = "0") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false,defaultValue = "10") Integer pageSize,
			@RequestHeader(value = "uid",required = true) String uid,
			HttpServletRequest request
			) {
		AppointmentDto dto=new AppointmentDto();
		dto.setUserId(Long.valueOf(uid));
		dto.setStatus(status);
		dto.setPageNum(pageNum);
		dto.setPageSize(pageSize);
		dto.setBeManage(true);
		return appointmentService.getAppointmentList(dto);
	}
	
	@ApiOperation(value = "用户取消预约")
	@RequestMapping(value = "/cancelAppointment", method = RequestMethod.POST)
	public HttpResult<Object> cancelAppointment(
			@ApiParam(name = "id", value = "预约id", required = true) @RequestParam(required = true) Long id,
			@RequestHeader(value = "uid",required = true) String uid,
			HttpServletRequest request
			) {
		TouristAppointment record=new TouristAppointment();
		record.setId(id);
		record.setStatus(2);
		return appointmentService.cancelAppointment(record);
	}
	
	
	@ApiOperation(value = "获取用户预约列表-管理后台")
	@RequestMapping(value = "/getAppointmentList_manage", method = RequestMethod.POST)
	public HttpResult<ListResult<TouristAppointment>> getAppointmentList_manage(
			@ApiParam(name = "mobile",value = "手机号",required = false) @RequestParam(required = false) String mobile,
			@ApiParam(name = "name",value = "姓名",required = false) @RequestParam(required = false) String name,
			@ApiParam(name = "serialNo",value = "预约编号",required = false) @RequestParam(required = false) String serialNo,
			@ApiParam(name = "createDateStart",value = "预约时间起",required = false) @RequestParam(required = false) String createDateStart,
			@ApiParam(name = "createDateEnd",value = "预约时间止",required = false) @RequestParam(required = false) String createDateEnd,
			@ApiParam(name = "playDate",value = "游玩时间",required = false) @RequestParam(required = false) String playDate,
			@ApiParam(name = "status",value = "预约状态:0待游玩1已游玩2已取消",required = false) @RequestParam(required = false) String status,
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false,defaultValue = "0") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false,defaultValue = "10") Integer pageSize
			) {
		AppointmentDto dto=new AppointmentDto();
		dto.setMobile(mobile);
		dto.setName(name);
		dto.setSerialNo(serialNo);
		dto.setCreateDateStart(createDateStart);
		dto.setCreateDateEnd(createDateEnd);
		dto.setPlayDate(playDate);
		dto.setStatus(status);
		dto.setPageNum(pageNum);
		dto.setPageSize(pageSize);
		dto.setBeManage(true);
		return appointmentService.getAppointmentList(dto);
		
	}
	
	@ApiOperation(value = "获取参观人列表")
	@RequestMapping(value = "/getCompanionList", method = RequestMethod.POST)
	public HttpResult<List<TouristCompanion>> getCompanionList(
			@ApiParam(name = "id",value = "预约记录id",required = true) @RequestParam(required = true) Long id,
			@RequestHeader(value = "uid",required = true) String uid
			) {
		CompanionDto dto=new CompanionDto();
		dto.setUid(Integer.valueOf(uid));
		dto.setId(id);
		return appointmentService.getCompanionList(dto);
		
	}
	
	@ApiOperation(value = "获取常用人列表")
	@RequestMapping(value = "/getCompanionHisList", method = RequestMethod.POST)
	public HttpResult<List<TouristCompanionHis>> getCompanionHisList(
			@RequestHeader(value = "uid",required = true) String uid
			) {
		CompanionHisDto dto=new CompanionHisDto();
		dto.setUid(Integer.valueOf(uid));
		return appointmentService.getCompanionHisList(dto);
		
	}
	
	@ApiOperation(value = "添加/编辑常用人")
	@RequestMapping(value = "/addOrUpdateCompanion", method = RequestMethod.POST)
	public HttpResult<Object> addCompanion(@Valid  CompanionHisVo vo,@RequestHeader(value = "uid",required = true) String uid,BindingResult result) {
		if(DataUtils.isNotEmpty(bindingResult(result))) {
			return HttpResult.failure(null, bindingResult(result));
		}
		vo.setUid(Integer.valueOf(uid));
		return appointmentService.addCompanion(vo);
	}
	
	@ApiOperation(value = "获取可预约景点信息：预约入口页面")
	@RequestMapping(value = "/getAppointmentScenic", method = RequestMethod.POST)
	public HttpResult<List<AppointmentScenicVo>> getAppointmentScenic() {
		return appointmentService.getAppointmentScenic();
	}
	
	@ApiOperation(value = "获取天安门天气信息")
	@RequestMapping(value = "/getWeather", method = RequestMethod.POST)
	public HttpResult<Weather> getWeather() {
		return appointmentService.getWeather();
	}
	
	@ApiOperation(value = "获取预约信息：可预约哪几天的")
	@RequestMapping(value = "/getAppointment", method = RequestMethod.POST)
	public HttpResult<List<AppointmentDateVo>> getAppointment(
			@ApiParam(name = "scenicId",value = "景点id",required = true) @RequestParam(required = true) Integer scenicId,
			@ApiParam(name = "playDate",value = "游玩时间",required = false) @RequestParam(required = false) String  playDate
			) {
		
		return appointmentService.getAppointment(scenicId,playDate);
	}
	
	@ApiOperation(value = "获取预约时间段信息")
	@RequestMapping(value = "/getAppointmentItem", method = RequestMethod.POST)
	public HttpResult<AppointmentDateItemVo> getAppointmentItem(
			@ApiParam(name = "itemId",value = "预约时间区间id",required = true) @RequestParam(required = true) long itemId,
			@ApiParam(name = "playDate",value = "游玩时间",required = true) @RequestParam(required = true) String playDate
			) {
		return appointmentService.getAppointmentItem(itemId,playDate);
	}
	
	@ApiOperation(value = "预约操作")
	@RequestMapping(value = "/doAppointment", method = RequestMethod.POST)
	public HttpResult<String> doAppointment(
			@ApiParam(name = "scenicId",value = "景点id",required = true) @RequestParam(required = true) Integer scenicId,
			@ApiParam(name = "playDate",value = "游玩时间yyyy-MM-dd",required = true) @RequestParam(required = true) String playDate,
			@ApiParam(name = "itemId",value = "预约时间区间id",required = true) @RequestParam(required = true) Integer itemId,
			@ApiParam(name = "companion",value = "参观人json数组",required = true) @RequestParam(required = true) String companion,
			@ApiParam(name = "deviceType", value = "请求终端：pc，ios，android", required = true) @RequestParam(required = true) String deviceType,
			@RequestHeader(value = "uid",required = true) String uid
			) {
		return appointmentService.doAppointment(scenicId,Long.valueOf(uid),playDate,companion,itemId,deviceType);
	}
}
