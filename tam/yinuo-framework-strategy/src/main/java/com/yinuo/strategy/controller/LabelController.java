package com.yinuo.strategy.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageInfo;
import com.inuol.entity.strategy.Label;
import com.yinuo.common.common.ErrCodeAndMsg;
import com.yinuo.common.common.HttpResult;
import com.yinuo.common.utils.StringUtil;
import com.yinuo.strategy.service.LabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/label")
@ApiSort(value = 1)
@Api(tags="旅游攻略标签")
public class LabelController {
    private  final Logger logger = LoggerFactory.getLogger(LabelController.class);
    @Autowired
    private LabelService labelService;

    @ApiOperation(value = "旅游攻略标签集合-app")
    @PostMapping(value = "/all")
    public HttpResult<List<Label>> all() {
        List<String> orderby =new ArrayList<String>();
        orderby.add("update_time");
        orderby.add("create_time");
        EntityWrapper<Label> wrapper = new EntityWrapper();
        wrapper.eq("status", 1).orderBy("orderby_num",true).orderDesc(orderby);
        List<Label> list = labelService.selectList(wrapper);
        return HttpResult.success(list);
    }

    @ApiOperation(value = "旅游攻略标签列表-pc")
    @PostMapping(value = "/list")
    public HttpResult<List<Label>> list(
                                        @ApiParam(name = "title", value = "标签名称", required = false) @RequestParam( required = false) String title,
                                        @ApiParam(name = "status", value = "状态", required = false) @RequestParam( required = false) Integer status,
                                        @ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam( required = false,defaultValue = "1") Integer pageNum,
                                        @ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam( required = false,defaultValue = "10") Integer pageSize) {

        List<String> orderby =new ArrayList<String>();
        orderby.add("update_time");
        orderby.add("create_time");
        EntityWrapper<Label> wrapper = new EntityWrapper();
        if(status!=0){
            wrapper.eq("status", status);
        }
        if(!StringUtil.isNull(title)){
            wrapper.like("title",title);
        }
        wrapper.orderBy("orderby_num",true).orderDesc(orderby);
        List<Label> list = labelService.selectList(wrapper);
        return HttpResult.success(list);
    }

    @ApiOperation(value = "旅游攻略标签新增-pc")
    @PostMapping(value = "/save")
    public HttpResult save(@ApiParam(name="传入对象",value="传入json格式",required=true) @RequestBody @Valid Label label, @ApiIgnore @RequestHeader(value = "uid") String uid) {
        if(StringUtil.isNull(uid)){
            return HttpResult.failure(ErrCodeAndMsg.REQ_PARAM_ERROR);
        }
        label.setCreateUserId(Long.parseLong(uid));
       if(labelService.insert(label)){
           return HttpResult.success();
       }else{
           return HttpResult.failure("保存失败");
       }
    }
    @ApiOperation(value = "旅游攻略标签更新-pc 注意id必传 其他属性修改什么就传什么")
    @PostMapping(value = "/update")
    public HttpResult update(@ApiParam(name="传入对象",value="传入json格式",required=true) @RequestBody  Label label, @ApiIgnore @RequestHeader(value = "uid") String uid) {
        if(label.getId()==null||StringUtil.isNull(uid)){
            return HttpResult.failure(ErrCodeAndMsg.REQ_PARAM_ERROR);
        }
        label.setUpdateUserId(Long.parseLong(uid));
        label.setUpdateTime(new Date());
        if(labelService.updateById(label)){
            return HttpResult.success();
        }else{
            return HttpResult.failure("修改失败");
        }
    }
}
