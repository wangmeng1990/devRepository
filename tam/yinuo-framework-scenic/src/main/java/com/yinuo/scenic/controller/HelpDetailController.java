package com.yinuo.scenic.controller;

import com.yinuo.common.common.HttpResult;
import com.inuol.vo.scenic.HelpDetailVo;
import com.yinuo.scenic.service.HelpDetailService;

import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Capejor
 * @date 2019-12-31 13:38
 */

@RestController
@RequestMapping("/helpDetail")
@Api(tags = "帮助详情")
public class HelpDetailController {

    @Autowired
    private HelpDetailService helpDetailService;

    @ApiOperation(value = "添加帮助详情")
    @PostMapping("/insertHelpDetail")
    public HttpResult insertHelpDetail(HelpDetailVo vo) {
        return helpDetailService.insertHelpDetail(vo);
    }

    @ApiOperation(value = "编辑帮助详情")
    @PostMapping("/updateHelpDetail")
    public HttpResult updateHelpDetail(HelpDetailVo vo) {
        return helpDetailService.updateHelpDetail(vo);
    }

    @ApiOperation(value = "查询所有帮助详情")
    @GetMapping("/selectAllHelpDetail")
    public HttpResult selectAllHelpDetail(@ApiParam(name = "pageNum", value = "页数") @RequestParam Integer pageNum,
                                          @ApiParam(name = "pageSize", value = "每页条数") @RequestParam Integer pageSize,
                                          @ApiParam(name = "question", value = "问题") @RequestParam(required = false) String question) {
        return helpDetailService.selectAllHelpDetail(pageNum, pageSize, question);
    }

    @ApiOperation(value = "删除帮助详情")
    @PostMapping("/deleteHelpDetail")
    public HttpResult deleteHelpDetail(@RequestParam Long id) {
        return helpDetailService.deleteOneById(id);
    }

    @ApiOperation(value = "启用禁用")
    @PostMapping("/updateHelpDetailState")
    public HttpResult updateHelpDetailState(
            @ApiParam(name = "id", value = "id", required = true) @RequestParam Long id,
            @ApiParam(name = "state", value = "状态：1启用0未启用3删除", required = true) String state) {
        return helpDetailService.updateHelpDetailState(id, state);
    }


}
