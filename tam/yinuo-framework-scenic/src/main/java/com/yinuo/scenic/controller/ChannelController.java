package com.yinuo.scenic.controller;

import com.yinuo.common.common.HttpResult;
import com.inuol.vo.scenic.ChannelVo;
import com.yinuo.scenic.service.ChannelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Capejor
 * @date 2020-01-06 17:14
 */
@Api(tags = "渠道管理")
@RestController
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @ApiOperation("添加渠道")
    @PostMapping("/insertChannel")
    public HttpResult insertChannel(ChannelVo vo) {
        return channelService.insertChannel(vo);
    }

    @ApiOperation("编辑渠道")
    @PostMapping("/updateChannel")
    public HttpResult updateChannel(ChannelVo vo) {
        return channelService.updateChannel(vo);
    }

    @ApiOperation("查询所有渠道")
    @PostMapping("/selectAllChannel")
    public HttpResult selectAllChannel(@ApiParam(name = "pageNum", value = "页数") @RequestParam Integer pageNum,
                                       @ApiParam(name = "pageSize", value = "每页条数") @RequestParam Integer pageSize,
                                       @ApiParam(name = "channelName", value = "渠道名称") @RequestParam(required = false) String channelName,
                                       @ApiParam(name = "channelCode", value = "渠道代码") @RequestParam(required = false) String channelCode) {
        return channelService.selectAllChannel(pageNum, pageSize, channelName, channelCode);
    }

    @ApiOperation("删除渠道")
    @PostMapping("/deleteChannel")
    public HttpResult deleteChannel(@ApiParam(name = "id", value = "id") @RequestParam Long id) {
        return channelService.deleteOneById(id);
    }

    @ApiOperation(value = "启用禁用")
    @PostMapping("/updateChannelState")
    public HttpResult updateChannelState(
            @ApiParam(name = "id", value = "id", required = true) @RequestParam Long id,
            @ApiParam(name = "state", value = "状态：1启用0未启用3删除", required = true) String state) {
        return channelService.updateChannelState(id, state);
    }

}
