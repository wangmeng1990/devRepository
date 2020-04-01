package com.yinuo.scenic.controller;

import com.yinuo.common.common.HttpResult;
import com.inuol.vo.scenic.RouteVo;
import com.yinuo.scenic.service.RouteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Capejor
 * @date 2020-01-02 11:04
 */
@Api(tags = "路线管理")
@RequestMapping("/route")
@RestController
public class RouteController {

    @Autowired
    private RouteService routeService;

    @ApiOperation(value = "添加路线")
    @PostMapping("/insertRoute")
    public HttpResult insertRoute(RouteVo vo) {
        int nameResult = routeService.judgeRepeatByRouteName(vo.getRouteName());
        if (nameResult > 0) {
            return HttpResult.failure("路线名称重复！");
        }
        return routeService.insertRoute(vo);
    }

    @ApiOperation(value = "编辑路线")
    @PostMapping("/updateRoute")
    public HttpResult updateRoute(RouteVo vo) {
        int nameResult = routeService.judgeRepeatByRouteNameExceptOwn(vo.getId(), vo.getRouteName());
        if (nameResult > 0) {
            return HttpResult.failure("路线名称重复！");
        }
        return routeService.updateRoute(vo);
    }

    @ApiOperation(value = "查询所有路线")
    @GetMapping("/selectAllRoute")
    public HttpResult selectAllRoute(@ApiParam(name = "pageNum", value = "页数") @RequestParam(required = false) Integer pageNum,
                                     @ApiParam(name = "pageSize", value = "每页条数") @RequestParam(required = false) Integer pageSize,
                                     @RequestParam(required = false) String routeName) {
        return routeService.selectAllRoute(pageNum, pageSize, routeName);
    }


    @ApiOperation(value = "删除路线")
    @PostMapping("/deleteRoute")
    public HttpResult deleteRoute(@RequestParam Long id) {
        return routeService.deleteOneById(id);
    }

}
