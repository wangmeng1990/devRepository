package com.yinuo.scenic.controller;

import com.yinuo.common.common.HttpResult;
import com.inuol.vo.scenic.ScenicAreaVo;
import com.yinuo.scenic.service.ScenicAreaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "景区管理")
@RestController
@RequestMapping("/scenicArea")
public class ScenicAreaController {

    @Autowired
    private ScenicAreaService scenicAreaService;


    @ApiOperation(value = "编辑景点信息")
    @PostMapping("/updateScenicArea")
    public HttpResult updateScenicArea(ScenicAreaVo scenicAreaVo){
        return scenicAreaService.updateScenicArea(scenicAreaVo);
    }


    @ApiOperation(value = "获取所有景点")
    @GetMapping("/selectAllScenicArea")
    public HttpResult selectAllScenicArea(@ApiParam(name = "pageNum", value = "页数", required = false) @RequestParam(required = false) Integer pageNum,
                                          @ApiParam(name = "pageSize", value = "每页条数", required = false) @RequestParam(required = false) Integer pageSize){
        return scenicAreaService.selectAllScenicArea(pageNum,pageSize);
    }



}
