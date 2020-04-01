package com.yinuo.scenic.controller;

import com.inuol.vo.scenic.ScenicSpotVo;
import com.yinuo.common.common.HttpResult;
import com.yinuo.scenic.service.ScenicSpotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Capejor
 * @date 2020-01-21 10:53
 */
@Api(tags = "景点管理")
@RestController
@RequestMapping("/scenicSpot")
public class ScenicSpotController {

    @Autowired
    private ScenicSpotService scenicSpotService;

    @ApiOperation("添加景点")
    @PostMapping("/insertScenicSpot")
    public HttpResult insertScenicSpot(@RequestBody ScenicSpotVo vo){
        return scenicSpotService.insertScenicSpot(vo);
    }


    @ApiOperation("编辑景点")
    @PostMapping("/updateScenicSpot")
    public HttpResult updateScenicSpot(@RequestBody ScenicSpotVo vo){
        return scenicSpotService.updateScenicSpot(vo);
    }


    @ApiOperation("查询景点")
    @GetMapping("/selectAllScenicSpot")
    public HttpResult selectAllScenicSpot(@ApiParam(name = "pageNum", value = "页数", required = false) @RequestParam(required = false) Integer pageNum,
                                          @ApiParam(name = "pageSize", value = "每页条数", required = false) @RequestParam(required = false) Integer pageSize,
                                          @RequestParam(required = false) Long scenicAreaId,
                                          @RequestParam(required = false) String name){
        return scenicSpotService.selectAllScenicSpot(pageNum,pageSize,scenicAreaId,name);
    }


    @ApiOperation("删除景点")
    @PostMapping("/deleteScenicSpot")
    public HttpResult deleteScenicSpot(Long id){
        return scenicSpotService.deleteScenicSpotById(id);
    }
}
