package com.yinuo.scenic.controller;

import com.inuol.vo.scenic.VrVo;
import com.yinuo.common.common.HttpResult;
import com.yinuo.scenic.service.VrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Capejor
 * @date 2020-01-03 15:51
 */
@Api(tags = "景点vr")
@RestController
@RequestMapping("/vr")
public class VrController {


    @Autowired
    private VrService vrService;

    @ApiOperation("添加文字描述")
    @PostMapping("/insertVrDescription")
    public HttpResult insertVrDescription(VrVo vo){
        return vrService.insertVrDescription(vo);
    }



    @ApiOperation("添加照片")
    @PostMapping("/insertVrPhoto")
    public HttpResult insertVrPhoto(VrVo vo){
        return vrService.insertVrPhoto(vo);
    }

}

