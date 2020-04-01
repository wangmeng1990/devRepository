package com.yinuo.scenic.controller;

import com.inuol.vo.scenic.ScenicLabelVo;
import com.yinuo.common.common.HttpResult;
import com.yinuo.scenic.service.ScenicLabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @author Capejor
 * @date 2020-02-11 11:40
 */
@Api(tags = "景区标签")
@RequestMapping("/scenicLabel")
@RestController
public class ScenicLabelController {

    @Autowired
    private ScenicLabelService scenicLabelService;

    @ApiOperation("添加景区标签")
    @PostMapping("/insertScenicLabel")
    public HttpResult insertScenicLabel(@RequestBody @Valid ScenicLabelVo vo){
        return scenicLabelService.insertScenicLabel(vo);
    }

    @ApiOperation("编辑景区标签")
    @PostMapping("/updateScenicLabel")
    public HttpResult updateScenicLabel(@RequestBody @Valid ScenicLabelVo vo){
        return scenicLabelService.updateScenicLabel(vo);
    }


    @ApiOperation("查询景区标签")
    @GetMapping("/selectAllScenicLabel")
    public HttpResult selectAllScenicLabel(@ApiParam(name = "pageNum", value = "页数", required = false) @RequestParam(required = false) Integer pageNum,
                                           @ApiParam(name = "pageSize", value = "每页条数", required = false) @RequestParam(required = false) Integer pageSize,
                                           @ApiParam(name = "labelName", value = "标签名称", required = false) @RequestParam String labelName){
        return scenicLabelService.selectAllScenicLabel(pageNum,pageSize,labelName);
    }



    @ApiOperation("删除景区标签")
    @PostMapping("/deleteScenicLabel")
    public HttpResult deleteScenicLabel(long id){
        return scenicLabelService.deleteOneById(id);
    }
}
