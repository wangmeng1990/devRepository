package com.yinuo.strategy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.github.pagehelper.PageInfo;
import com.inuol.dto.strategy.CollectionsDto;
import com.inuol.dto.strategy.CommentDto;
import com.inuol.dto.strategy.CommentPraisesDto;
import com.inuol.dto.strategy.PraisesDto;
import com.inuol.vo.strategy.TravelCommentVo;
import com.inuol.vo.strategy.TravelVo;
import com.yinuo.common.common.ErrCodeAndMsg;
import com.yinuo.common.utils.StringUtil;
import com.yinuo.common.web.BaseController;
import com.yinuo.strategy.service.TravelService;
import io.swagger.annotations.ApiSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.inuol.entity.strategy.Travel;
import com.yinuo.common.common.HttpResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/travel")
@ApiSort(value = 1)
@Api(tags="旅游攻略-app")
public class TravelController  {
	private  final Logger logger = LoggerFactory.getLogger(TravelController.class);
	@Autowired
	private TravelService travelService;
	@ApiOperation(value="发布攻略-app", notes="发布攻略", produces="application/json",position=1)
	@PostMapping("/save")
	public HttpResult<Map> saveTravel(@ApiParam(name="传入对象",value="传入json格式",required=true) @RequestBody @Valid Travel travel, @ApiIgnore @RequestHeader(value = "uid") String uid) {
		// 判断
		if(travel.getAnnexType()==1){
			//图片 检查图片数量和设置首图
			String annexUrls = travel.getAnnexUrls();
			String [] annexUrlArr = annexUrls.split(",");
			if(annexUrlArr.length>=10){
				return HttpResult.failure("400", "图片最多上传10张");
			}
			travel.setImg(annexUrlArr[0]);
		}
		if(StringUtil.isNull(uid)){
			return HttpResult.failure(ErrCodeAndMsg.REQ_PARAM_ERROR);
		}
		travel.setCreateUserId(Long.parseLong(uid));
		travelService.insertTravel(travel);
		Map resultMap = new HashMap();
		if(travel.getId()!=null){
			resultMap.put("id",travel.getId());
		}else{
			return HttpResult.failure(ErrCodeAndMsg.JUST_MOMENT_ERROR);
		}
		return HttpResult.success(resultMap);
	}

	@ApiOperation(value = "攻略列表-app",position=2)
	@PostMapping(value = "/list")
	public HttpResult<PageInfo<Travel>> selectList(
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam( required = false,defaultValue = "1") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam( required = false,defaultValue = "10") Integer pageSize) {

		return travelService.selectList(pageNum,pageSize);
	}
	@ApiOperation(value = "我的攻略列表-app",position=2)
	@PostMapping(value = "/userlist")
	public HttpResult<PageInfo<Travel>> userlist(
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam( required = false,defaultValue = "1") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam( required = false,defaultValue = "10") Integer pageSize,
			@ApiIgnore @RequestHeader(value = "uid") String uid) {
		if(StringUtil.isNull(uid)){
			return HttpResult.failure(ErrCodeAndMsg.REQ_PARAM_ERROR);
		}
		return travelService.selectUserList(pageNum,pageSize,uid);
	}

	@ApiOperation(value = "攻略详情-app",position=3)
	@PostMapping(value = "/detail")
	public HttpResult<TravelVo> detail(
			@ApiParam(name = "id", value = "攻略详情id", required = true) @RequestParam( required = true) Long id,
			@ApiParam(name = "userPageSize", value = "点赞人数数据条数 默认10", required = false) @RequestParam( required = false,defaultValue = "10") Integer userPageSize) {
		return travelService.selectTravelDetail(id,userPageSize);
	}

	@ApiOperation(value = "攻略点赞/取消点赞-app",position=4)
	@PostMapping(value = "/praises")
	public HttpResult praises(
			@ApiParam(name="传入对象",value="传入json格式",required=true) @RequestBody @Valid PraisesDto praisesDto,@ApiIgnore @RequestHeader(value = "uid") String uid) {
		if(StringUtil.isNull(uid)){
			return HttpResult.failure(ErrCodeAndMsg.REQ_PARAM_ERROR);
		}
		praisesDto.setUid(Long.parseLong(uid));
		return travelService.praises(praisesDto);
	}

	@ApiOperation(value = "攻略评论新增-app",position=5)
	@PostMapping(value = "/comment")
	public HttpResult comment(
			@ApiParam(name="传入对象",value="传入json格式",required=true) @RequestBody @Valid CommentDto commentDto,@ApiIgnore @RequestHeader(value = "uid") String uid) {
		if(StringUtil.isNull(uid)){
			return HttpResult.failure(ErrCodeAndMsg.REQ_PARAM_ERROR);
		}
		commentDto.setUid(Long.parseLong(uid));
		return travelService.insertComment(commentDto);
	}

	@ApiOperation(value = "攻略评论删除-app",position=6)
	@PostMapping(value = "/commentDel")
	public HttpResult commentDel(
			@ApiParam(name="传入对象",value="传入json格式",required=true) @RequestBody  CommentDto commentDto,@ApiIgnore @RequestHeader(value = "uid") String uid) {
		if (commentDto.getId()==null || commentDto.getTid()==null || StringUtil.isNull(uid)) {
			return HttpResult.failure(ErrCodeAndMsg.REQ_PARAM_ERROR);
		}
		commentDto.setUid(Long.parseLong(uid));
		return travelService.deleteComment(commentDto);
	}
	@ApiOperation(value = "攻略评论列表-app",position=2)
	@PostMapping(value = "/commentList")
	public HttpResult<PageInfo<TravelCommentVo>> commentList(
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam( required = false,defaultValue = "1") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam( required = false,defaultValue = "10") Integer pageSize,
			@ApiParam(name = "id", value = "攻略id", required = true) @RequestParam( required = true) Long id
			 ) {
		if(id==null){
			return HttpResult.failure(ErrCodeAndMsg.REQ_PARAM_ERROR);
		}
		return travelService.selectTravelCommentList(pageNum,pageSize,id);
	}

	@ApiOperation(value = "攻略评论点赞/取消点赞-app",position=4)
	@PostMapping(value = "/commentPraises")
	public HttpResult commentPraises(
			@ApiParam(name="传入对象",value="传入json格式",required=true) @RequestBody @Valid CommentPraisesDto commentPraisesDto,@ApiIgnore @RequestHeader(value = "uid") String uid) {
		if(StringUtil.isNull(uid)){
			return HttpResult.failure(ErrCodeAndMsg.REQ_PARAM_ERROR);
		}
		commentPraisesDto.setUid(Long.parseLong(uid));
		return travelService.commentPraises(commentPraisesDto);
	}

	@ApiOperation(value = "攻略收藏新增-app",position=5)
	@PostMapping(value = "/addCollections")
	public HttpResult addCollections(
			@ApiParam(name="传入对象",value="传入json格式",required=true) @RequestBody @Valid CollectionsDto collectionsDto,@ApiIgnore @RequestHeader(value = "uid") String uid) {
		if(StringUtil.isNull(uid)){
			return HttpResult.failure(ErrCodeAndMsg.REQ_PARAM_ERROR);
		}
		collectionsDto.setUid(Long.parseLong(uid));
		return travelService.insertCollections(collectionsDto);
	}

	@ApiOperation(value = "攻略收藏删除-app",position=5)
	@PostMapping(value = "/deleteCollections")
	public HttpResult deleteCollections(
			@ApiParam(name="传入对象",value="传入json格式",required=true) @RequestBody @Valid CollectionsDto collectionsDto,@ApiIgnore @RequestHeader(value = "uid") String uid) {
		if(StringUtil.isNull(uid)||collectionsDto.getTid()==null){
			return HttpResult.failure(ErrCodeAndMsg.REQ_PARAM_ERROR);
		}
		collectionsDto.setUid(Long.parseLong(uid));
		return travelService.deleteCollections(collectionsDto);
	}

	@ApiOperation(value = "我的收藏攻略列表-app",position=2)
	@PostMapping(value = "/userCollectionslist")
	public HttpResult<PageInfo<Travel>> userCollectionslist(
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam( required = false,defaultValue = "1") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam( required = false,defaultValue = "10") Integer pageSize,
			@ApiIgnore @RequestHeader(value = "uid") String uid) {
		if(StringUtil.isNull(uid)){
			return HttpResult.failure(ErrCodeAndMsg.REQ_PARAM_ERROR);
		}
		return travelService.selectUserCollectionsList(pageNum,pageSize,uid);
	}

}
