package com.yinuo.manage.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inuol.dto.BaseRequestDto;
import com.inuol.dto.PopRequestDto;
import com.inuol.vo.BannerVo;
import com.inuol.vo.CulturalCreationVo;
import com.inuol.vo.FunctionVo;
import com.inuol.vo.InformationVo;
import com.inuol.vo.MessageVo;
import com.inuol.vo.NoticeVo;
import com.inuol.vo.PopVo;
import com.inuol.vo.VideoVo;
import com.yinuo.common.common.HttpResult;
import com.yinuo.manage.service.ManageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value ="/manageController" )
@Api(tags="中台管理")
public class ManageController {
	@Autowired
	private ManageService manageService;

	@ApiOperation(value = "获取弹窗列表")
	@RequestMapping(value = "/getPopList", method = RequestMethod.POST)
	public HttpResult<Object> getPopList(
			@ApiParam(name = "deviceType", value = "请求终端：pc，ios，android", required = true) @RequestParam(required = true) String deviceType) {
		PopRequestDto pop = new PopRequestDto();
		pop.setDeviceType(deviceType);
		return manageService.getPopList(pop);
	}

	@ApiOperation(value = "获取弹窗列表-管理后台")
	@RequestMapping(value = "/getPopList_manage", method = RequestMethod.POST)
	public HttpResult<Object> getPopList_manage(
			@ApiParam(name = "name", value = "弹窗名称", required = false) @RequestParam(required = false) String name,
			@ApiParam(name = "type", value = "弹窗类型", required = false) @RequestParam(required = false) String type,
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false) Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false) Integer pageSize) {
		PopRequestDto pop = new PopRequestDto();
		pop.setBeManage(true);
		pop.setName(name);
		pop.setName(type);
		pop.setPageNum(pageNum);
		pop.setPageSize(pageSize);
		return manageService.getPopList(pop);
	}

	@ApiOperation(value = "新增/更新弹窗信息")
	@RequestMapping(value = "/updateOrSavePop", method = RequestMethod.POST)
	public HttpResult<Object> updateOrSavePop(@Validated PopVo pop) {
		return manageService.updateOrSavePop(pop);
	}

	@ApiOperation(value = "更新弹窗状态")
	@RequestMapping(value = "/updatePopState", method = RequestMethod.POST)
	public HttpResult<Object> updatePopState(
			@ApiParam(name = "id", value = "数据id", required = true) @RequestParam(required = true) Long id,
			@ApiParam(name = "state", value = "状态：1启用0未启用3删除", required = true) @RequestParam(required = true) int state) {
		return manageService.updatePopState(id, state);
	}

	@ApiOperation(value = "获取消息列表")
	@RequestMapping(value = "/getMessageList", method = RequestMethod.POST)
	public HttpResult<Object> getMessageList(
			@ApiParam(name = "deviceType", value = "请求终端：pc，ios，android", required = true) @RequestParam(required = true) String deviceType
			) {
		BaseRequestDto dto=new PopRequestDto();
		dto.setDeviceType(deviceType);
		return manageService.getMessageList("", "", dto);
	}
	@ApiOperation(value = "获取消息列表-管理后台")
	@RequestMapping(value = "/getMessageList_manage", method = RequestMethod.POST)
	public HttpResult<Object> getMessageList_manage(
			@ApiParam(name = "type", value = "消息类型", required = false) @RequestParam(required = false) String type,
			@ApiParam(name = "subJect", value = "消息主题", required = false) @RequestParam(required = false) String subJect,
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false,defaultValue ="0") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false,defaultValue = "10") Integer pageSize
			) {
		BaseRequestDto dto=new BaseRequestDto();
		dto.setBeManage(true);
		dto.setPageNum(pageNum);
		dto.setPageSize(pageSize);
		return manageService.getMessageList(type, subJect, dto);
	}
	@ApiOperation(value = "新增/更新消息信息")
	@RequestMapping(value = "/updateOrSaveMessage", method = RequestMethod.POST)
	public HttpResult<Object> updateOrSaveMessage(@Validated MessageVo vo) {
		return manageService.updateOrSaveMessage(vo);
	}

	@ApiOperation(value = "更新消息状态")
	@RequestMapping(value = "/updateMessageState", method = RequestMethod.POST)
	public HttpResult<Object> updateMessageState(
			@ApiParam(name = "id", value = "数据id", required = true) @RequestParam(required = true) Long id,
			@ApiParam(name = "state", value = "状态：1启用0未启用3删除", required = true) @RequestParam(required = true) int state) {
		return manageService.updateMessageState(id, state);
	}

	@ApiOperation(value = "获取公告列表")
	@RequestMapping(value = "/getNoticeList", method = RequestMethod.POST)
	public HttpResult<Object> getNoticeList(
			@ApiParam(name = "subJect", value = "主题", required = false) @RequestParam(required = false) String subJect,
			@ApiParam(name = "deviceType", value = "请求终端：pc，ios，android", required = true) @RequestParam(required = true) String deviceType
			) {
		BaseRequestDto dto=new BaseRequestDto();
		dto.setDeviceType(deviceType);
		return manageService.getNoticeList(subJect, dto);
	}

	@ApiOperation(value = "获取公告列表-管理后台")
	@RequestMapping(value = "/getNoticeList_manage", method = RequestMethod.POST)
	public HttpResult<Object> getNoticeList_manage(
			@ApiParam(name = "subJect", value = "主题", required = false) @RequestParam(required = false) String subJect,
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false,defaultValue ="0") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false,defaultValue = "10") Integer pageSize
			) {
		BaseRequestDto dto=new BaseRequestDto();
		dto.setBeManage(true);
		dto.setPageNum(pageNum);
		dto.setPageSize(pageSize);
		return manageService.getNoticeList(subJect, dto);
	}
	
	@ApiOperation(value = "新增/更新公告信息")
	@RequestMapping(value = "/updateOrSaveNotice", method = RequestMethod.POST)
	public HttpResult<Object> updateOrSaveNotice(@Validated NoticeVo vo) {
		return manageService.updateOrSaveNotice(vo);
	}

	@ApiOperation(value = "更新公告状态")
	@RequestMapping(value = "/updateNoticeState", method = RequestMethod.POST)
	public HttpResult<Object> updateNoticeState(
			@ApiParam(name = "id", value = "数据id", required = true) @RequestParam(required = true) Long id,
			@ApiParam(name = "state", value = "状态：1启用0未启用3删除", required = true) @RequestParam(required = true) int state) {
		return manageService.updateNoticeState(id, state);
	}

	@ApiOperation(value = "获取banner列表")
	@RequestMapping(value = "/getBannerList", method = RequestMethod.POST)
	public HttpResult<Object> getBannerList(
			@ApiParam(name = "type", value = "分类", required = false) @RequestParam(required = false) String type,
			@ApiParam(name = "subJect", value = "主题", required = false) @RequestParam(required = false) String subJect,
			@ApiParam(name = "deviceType", value = "请求终端：pc，ios，android", required = true) @RequestParam(required = true) String deviceType
			) {
		BaseRequestDto dto=new BaseRequestDto();
		dto.setDeviceType(deviceType);
		return manageService.getBannerList(type, subJect, dto);
	}

	@ApiOperation(value = "获取banner列表-管理后台")
	@RequestMapping(value = "/getBannerList_manage", method = RequestMethod.POST)
	public HttpResult<Object> getBannerList_manage(
			@ApiParam(name = "type", value = "分类", required = false) @RequestParam(required = false) String type,
			@ApiParam(name = "subJect", value = "主题", required = false) @RequestParam(required = false) String subJect,
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false,defaultValue ="0") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false,defaultValue = "10") Integer pageSize
			) {
		BaseRequestDto dto=new BaseRequestDto();
		dto.setBeManage(true);
		dto.setPageNum(pageNum);
		dto.setPageSize(pageSize);
		return manageService.getBannerList(type, subJect, dto);
	}
	
	@ApiOperation(value = "新增/更新banner信息")
	@RequestMapping(value = "/updateOrSaveBanner", method = RequestMethod.POST)
	public HttpResult<Object> updateOrSaveBanner(@Validated BannerVo vo) {
		return manageService.updateOrSaveBanner(vo);
	}

	@ApiOperation(value = "更新banner状态")
	@RequestMapping(value = "/updateBannerState", method = RequestMethod.POST)
	public HttpResult<Object> updateBannerState(
			@ApiParam(name = "id", value = "数据id", required = true) @RequestParam(required = true) Long id,
			@ApiParam(name = "state", value = "状态：1启用0未启用3删除", required = true) @RequestParam(required = true) int state) {
		return manageService.updateBannerState(id, state);
	}

	@ApiOperation(value = "获取专题列表")
	@RequestMapping(value = "/getSubjectList", method = RequestMethod.POST)
	public HttpResult<Object> getSubjectList(
			@ApiParam(name = "subJect", value = "主题", required = false) @RequestParam(required = false) String subJect,
			@ApiParam(name = "deviceType", value = "请求终端：pc，ios，android", required = true) @RequestParam(required = true) String deviceType
			) {
		BaseRequestDto dto=new BaseRequestDto();
		dto.setDeviceType(deviceType);
		return manageService.getSubjectList(subJect, dto);
	}

	@ApiOperation(value = "获取专题列表-管理后台")
	@RequestMapping(value = "/getSubjectList_manage", method = RequestMethod.POST)
	public HttpResult<Object> getSubjectList_manage(
			@ApiParam(name = "subJect", value = "主题", required = false) @RequestParam(required = false) String subJect,
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false,defaultValue ="0") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false,defaultValue = "10") Integer pageSize
			) {
		BaseRequestDto dto=new BaseRequestDto();
		dto.setBeManage(true);
		dto.setPageNum(pageNum);
		dto.setPageSize(pageSize);
		return manageService.getSubjectList(subJect, dto);
	}
	
	@ApiOperation(value = "新增/更新专题信息")
	@RequestMapping(value = "/updateOrSaveSubject", method = RequestMethod.POST)
	public HttpResult<Object> updateOrSaveSubject(@Validated BannerVo vo) {
		return manageService.updateOrSaveSubject(vo);
	}

	@ApiOperation(value = "更新专题状态")
	@RequestMapping(value = "/updateSubjectState", method = RequestMethod.POST)
	public HttpResult<Object> updateSubjectState(
			@ApiParam(name = "id", value = "数据id", required = true) @RequestParam(required = true) Long id,
			@ApiParam(name = "state", value = "状态：1启用0未启用3删除", required = true) @RequestParam(required = true) int state) {
		return manageService.updateSubjectState(id, state);
	}

	@ApiOperation(value = "获取资讯列表")
	@RequestMapping(value = "/getInformationList", method = RequestMethod.POST)
	public HttpResult<Object> getInformationList(
			@ApiParam(name = "subJect", value = "主题", required = false) @RequestParam(required = false) String subJect,
			@ApiParam(name = "deviceType", value = "请求终端：pc，ios，android", required = true) @RequestParam(required = true) String deviceType
			) {
		BaseRequestDto dto=new BaseRequestDto();
		dto.setDeviceType(deviceType);
		return manageService.getInformationList(subJect, dto);
	}
	@ApiOperation(value = "获取资讯列表-管理后台")
	@RequestMapping(value = "/getInformationList_manage", method = RequestMethod.POST)
	public HttpResult<Object> getInformationList_manage(
			@ApiParam(name = "subJect", value = "主题", required = false) @RequestParam(required = false) String subJect,
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false,defaultValue ="0") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false,defaultValue = "10") Integer pageSize
			) {
		BaseRequestDto dto=new BaseRequestDto();
		dto.setBeManage(true);
		dto.setPageNum(pageNum);
		dto.setPageSize(pageSize);
		return manageService.getInformationList(subJect, dto);
	}

	@ApiOperation(value = "新增/更新资讯信息")
	@RequestMapping(value = "/updateOrSaveInformation", method = RequestMethod.POST)
	public HttpResult<Object> updateOrSaveInformation(@Validated InformationVo vo) {
		return manageService.updateOrSaveInformation(vo);
	}

	@ApiOperation(value = "更新资讯状态")
	@RequestMapping(value = "/updateInformationState", method = RequestMethod.POST)
	public HttpResult<Object> updateInformationState(
			@ApiParam(name = "id", value = "数据id", required = true) @RequestParam(required = true) Long id,
			@ApiParam(name = "state", value = "状态：1启用0未启用3删除", required = true) @RequestParam(required = true) int state) {
		return manageService.updateInformationState(id, state);
	}

	@ApiOperation(value = "获取首页功能列表")
	@RequestMapping(value = "/getFunctionList", method = RequestMethod.POST)
	public HttpResult<Object> getFunctionList(
			@ApiParam(name = "name", value = "名称", required = false) @RequestParam(required = false) String name,
			@ApiParam(name = "deviceType", value = "请求终端：pc，ios，android", required = true) @RequestParam(required = true) String deviceType
			) {
		BaseRequestDto dto=new BaseRequestDto();
		return manageService.getFunctionList(name, dto);
	}

	@ApiOperation(value = "获取首页功能列表-管理后台")
	@RequestMapping(value = "/getFunctionList_manage", method = RequestMethod.POST)
	public HttpResult<Object> getFunctionList_manage(
			@ApiParam(name = "name", value = "名称", required = false) @RequestParam(required = false) String name,
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false,defaultValue ="0") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false,defaultValue = "10") Integer pageSize
			) {
		BaseRequestDto dto=new BaseRequestDto();
		dto.setBeManage(true);
		dto.setPageNum(pageNum);
		dto.setPageSize(pageSize);
		return manageService.getFunctionList(name, dto);
	}
	
	
	@ApiOperation(value = "新增/更新首页功能信息")
	@RequestMapping(value = "/updateOrSaveFunction", method = RequestMethod.POST)
	public HttpResult<Object> updateOrSaveFunction(@Validated FunctionVo vo) {
		return manageService.updateOrSaveFunction(vo);
	}

	@ApiOperation(value = "更新首页功能状态")
	@RequestMapping(value = "/updateFunctionState", method = RequestMethod.POST)
	public HttpResult<Object> updateFunctionState(
			@ApiParam(name = "id", value = "数据id", required = true) @RequestParam(required = true) Long id,
			@ApiParam(name = "state", value = "状态：1启用0未启用3删除", required = true) @RequestParam(required = true) int state) {
		return manageService.updateFunctionState(id, state);
	}

	@ApiOperation(value = "获取视频列表")
	@RequestMapping(value = "/getVideoList", method = RequestMethod.POST)
	public HttpResult<Object> getVideoList(
			@ApiParam(name = "type", value = "类型", required = false) @RequestParam(required = false) String type,
			@ApiParam(name = "classify", value = "分类", required = false) @RequestParam(required = false) String classify,
			@ApiParam(name = "subJect", value = "标题", required = false) @RequestParam(required = false) String subJect,
			@ApiParam(name = "deviceType", value = "请求终端：pc，ios，android", required = true) @RequestParam(required = true) String deviceType
			) {
		BaseRequestDto dto=new BaseRequestDto();
		dto.setDeviceType(deviceType);
		return manageService.getVideoList(type, classify, subJect, dto);
	}
	
	@ApiOperation(value = "获取视频列表-管理后台")
	@RequestMapping(value = "/getVideoList_manage", method = RequestMethod.POST)
	public HttpResult<Object> getVideoList_manage(
			@ApiParam(name = "type", value = "类型", required = false) @RequestParam(required = false) String type,
			@ApiParam(name = "classify", value = "分类", required = false) @RequestParam(required = false) String classify,
			@ApiParam(name = "subJect", value = "标题", required = false) @RequestParam(required = false) String subJect,
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false,defaultValue ="0") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false,defaultValue = "10") Integer pageSize
			) {
		BaseRequestDto dto=new BaseRequestDto();
		dto.setBeManage(true);
		dto.setPageNum(pageNum);
		dto.setPageSize(pageSize);
		return manageService.getVideoList(type, classify, subJect, dto);
	}
	
	@ApiOperation(value = "新增/更新视频信息")
	@RequestMapping(value = "/updateOrSaveVideo", method = RequestMethod.POST)
	public HttpResult<Object> updateOrSaveVideo(@Validated VideoVo vo) {
		return manageService.updateOrSaveVideo(vo);
	}

	@ApiOperation(value = "更新视频状态")
	@RequestMapping(value = "/updateVideoState", method = RequestMethod.POST)
	public HttpResult<Object> updateVideoState(
			@ApiParam(name = "id", value = "数据id", required = true) @RequestParam(required = true) Long id,
			@ApiParam(name = "state", value = "状态：1启用0未启用3删除", required = true) @RequestParam(required = true) int state) {
		return manageService.updateVideoState(id, state);
	}

	@ApiOperation(value = "获取文创列表")
	@RequestMapping(value = "/getCulturalCreationList", method = RequestMethod.POST)
	public HttpResult<Object> getCulturalCreationList(
			@ApiParam(name = "backGround", value = "背景图名称", required = false) @RequestParam(required = false) String backGround,
			@ApiParam(name = "backGroundType", value = "背景图类型", required = false) @RequestParam(required = false) String backGroundType,
			@ApiParam(name = "deviceType", value = "请求终端：pc，ios，android", required = true) @RequestParam(required = true) String deviceType
			) {
		BaseRequestDto dto=new BaseRequestDto();
		dto.setDeviceType(deviceType);
		return manageService.getCulturalCreationList(backGround, backGroundType, dto);
	}

	
	@ApiOperation(value = "获取文创列表-管理后台")
	@RequestMapping(value = "/getCulturalCreationList_manage", method = RequestMethod.POST)
	public HttpResult<Object> getCulturalCreationList_manage(
			@ApiParam(name = "backGround", value = "背景图名称", required = false) @RequestParam(required = false) String backGround,
			@ApiParam(name = "backGroundType", value = "背景图类型", required = false) @RequestParam(required = false) String backGroundType,
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false,defaultValue ="0") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false,defaultValue = "10") Integer pageSize
			) {
		BaseRequestDto dto=new BaseRequestDto();
		dto.setBeManage(true);
		dto.setPageNum(pageNum);
		dto.setPageSize(pageSize);
		return manageService.getCulturalCreationList(backGround, backGroundType, dto);
	}
	
	@ApiOperation(value = "新增/更新文创信息")
	@RequestMapping(value = "/updateOrSaveCulturalCreation", method = RequestMethod.POST)
	public HttpResult<Object> updateOrSaveCulturalCreation(@Validated CulturalCreationVo vo) {
		return manageService.updateOrSaveCulturalCreation(vo);
	}

	@ApiOperation(value = "更新文创状态pc")
	@RequestMapping(value = "/updateCulturalCreationState", method = RequestMethod.POST)
	public HttpResult<Object> updateCulturalCreationState(
			@ApiParam(name = "id", value = "数据id", required = true) @RequestParam(required = true) Long id,
			@ApiParam(name = "state", value = "状态：1启用0未启用3删除", required = true) @RequestParam(required = true) int state) {
		return manageService.updateCulturalCreationState(id, state);
	}
}
