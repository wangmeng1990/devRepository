package com.yinuo.strategy.controller;

import com.github.pagehelper.PageInfo;
import com.inuol.vo.strategy.TravelPcVo;
import com.yinuo.common.common.ErrCodeAndMsg;
import com.yinuo.common.common.HttpResult;
import com.yinuo.common.utils.StringUtil;
import com.yinuo.strategy.service.TravelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/travelPc")
@Api(tags="旅游攻略-pc")
public class TravelPcController {
    private  final Logger logger = LoggerFactory.getLogger(TravelPcController.class);
    @Autowired
    private TravelService travelService;
    @ApiOperation(value = "攻略列表-pc",position=2)
    @PostMapping(value = "/list")
    public HttpResult<PageInfo<TravelPcVo>> selectList(
            @ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam( required = false,defaultValue = "1") Integer pageNum,
            @ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam( required = false,defaultValue = "10") Integer pageSize,
            @ApiParam(name = "title", value = "攻略关键字", required = false) @RequestParam( required = false) String title,
            @ApiParam(name = "phone", value = "用户手机号", required = false) @RequestParam( required = false) String phone,
            @ApiParam(name = "createAddressName", value = "定位景区", required = false) @RequestParam( required = false) String createAddressName,
            @ApiParam(name = "status", value = "状态", required = false) @RequestParam( required = false) Integer status,
            @ApiParam(name = "createTimeStartStr", value = "发布时间开始 2019-12-21 01:12:11", required = false) @RequestParam( required = false) String createTimeStartStr,
            @ApiParam(name = "createTimeEndStr", value = "发布时间结束 2019-12-22 23:59:59", required = false) @RequestParam( required = false) String createTimeEndStr
        ) {
        Map parm = new HashMap();
        if(!StringUtil.isNull(title)){
            parm.put("title",title);
        }
        if(!StringUtil.isNull(phone)){
            parm.put("phone",phone);
        }
        if(!StringUtil.isNull(createAddressName)){
            parm.put("createAddressName",createAddressName);
        }
        if(status!=null){
            parm.put("status",status);
        }
        if(!StringUtil.isNull(createTimeStartStr)){
            parm.put("createTimeStartStr",createTimeStartStr);
        }
        if(!StringUtil.isNull(createTimeEndStr)){
            parm.put("createTimeEndStr",createTimeEndStr);
        }
        return travelService.selectTravelPcVoList(pageNum,pageSize,parm);
    }

    //置顶
    @ApiOperation(value = "攻略置顶-pc")
    @PostMapping(value = "/top")
    public HttpResult top(
                @ApiParam(name = "id", value = "旅游攻略id", required = true) @RequestParam( required = true) Long id
    ){
        return travelService.top(id);
    }
    // 攻略审核/屏蔽
    @ApiOperation(value = "攻略审核/屏蔽-pc")
    @PostMapping(value = "/updateStatus")
    public HttpResult updateStatus(
            @ApiParam(name = "id", value = "旅游攻略id", required = true) @RequestParam( required = true) Long id,
            @ApiParam(name = "status", value = "旅游攻略状态", required = true) @RequestParam( required = true) Integer status,
            @ApiIgnore @RequestHeader(value = "uid") String uid
    ){
        if(StringUtil.isNull(uid)){
            return HttpResult.failure(ErrCodeAndMsg.REQ_PARAM_ERROR);
        }
        return travelService.updateStatus(id,status,Long.parseLong(uid));
    }
    // 详情 和详情评论列表

    //评论屏蔽/取消屏蔽
    @ApiOperation(value = "评论屏蔽/取消屏蔽-pc")
    @PostMapping(value = "/updateCommentStatus")
    public HttpResult updateCommentStatus(
            @ApiParam(name = "id", value = "旅游攻略评论id", required = true) @RequestParam( required = true) long id,
            @ApiParam(name = "status", value = "旅游攻略评论状态 1正常 2删除 3屏蔽", required = true) @RequestParam( required = true) Integer status,
            @ApiIgnore @RequestHeader(value = "uid") String uid
    ){
        if(StringUtil.isNull(uid)){
            return HttpResult.failure(ErrCodeAndMsg.REQ_PARAM_ERROR);
        }
        return travelService.updateCommentStatus(id,status,Long.parseLong(uid));
    }
}
