package com.yinuo.scenic.controller;

import com.yinuo.common.common.HttpResult;
import com.inuol.vo.scenic.TourismHelpVo;
import com.yinuo.scenic.service.TourismHelpService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author Capejor
 * @date 2019-12-30 16:43
 */
@RestController
@RequestMapping("/tourismHelp")
@Api(tags = "旅游帮助")
public class TourismHelpController {

    @Autowired
    private TourismHelpService tourismHelpService;

    @ApiOperation(value = "添加旅游帮助")
    @PostMapping("/insertTourismHelp")
    public HttpResult insertTourismHelp(TourismHelpVo vo) {
        return tourismHelpService.insertTourismHelp(vo);
    }


    @ApiOperation(value = "编辑旅游帮助")
    @PostMapping("/updateTourismHelp")
    public HttpResult updateTourismHelp(TourismHelpVo vo) {
        return tourismHelpService.updateTourismHelp(vo);

    }

    @ApiOperation(value = "查询所有旅游帮助")
    @GetMapping("/selectAllTourismHelp")
    public HttpResult selectAll(@ApiParam(name = "pageNum", value = "页数", required = false) @RequestParam(required = false) Integer pageNum,
                            @ApiParam(name = "pageSize", value = "每页条数", required = false) @RequestParam(required = false) Integer pageSize,
                            @RequestParam(required = false) String questionType) {
        return tourismHelpService.selectAllTourismHelp(pageNum, pageSize, questionType);
    }

    @ApiOperation(value = "删除旅游帮助")
    @PostMapping("/deleteTourismHelp")
    public HttpResult deleteTourismHelp(@ApiParam(name = "id") @RequestParam Long id) {
        return tourismHelpService.deleteOneById(id);

    }

    @ApiOperation(value = "启用禁用")
    @PostMapping("/updateTourismHelpState")
    public HttpResult updateCulturalCreationState(
            @ApiParam(name = "id",value = "id",required = true) @RequestParam Long id,
            @ApiParam(name = "state",value = "状态：1启用,2禁用,3删除",required = true) String state) {
        return tourismHelpService.updateTourismHelpState(id,state);
    }

}
