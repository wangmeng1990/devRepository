package com.yinuo.welfare.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.inuol.entity.strategy.Label;
import com.inuol.entity.strategy.Travel;
import com.inuol.entity.welfare.RedBags;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.yinuo.common.common.CommonConstants;
import com.yinuo.common.common.ErrCodeAndMsg;
import com.yinuo.common.common.HttpResult;
import com.yinuo.common.utils.StringUtil;
import com.yinuo.welfare.service.RedBagsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/welfares")
@ApiSort(value = 1)
@Api(tags="福利中心-pc")
public class WelfareController {
    private  final Logger logger = LoggerFactory.getLogger(WelfareController.class);
    @Autowired
    private RedBagsService redBagsService;





    public static void main(String[] args) {
        String equipmentNo = StringUtil.getNewEquipmentNo(CommonConstants.WELFARE_REDBAGS, "10000");
        String res = StringUtil.getEquipmentNo(CommonConstants.WELFARE_REDBAGS,equipmentNo);
        System.out.println("生成设备编号：" + equipmentNo);
        System.out.println("编号：" + res);
    }

    @ApiOperation(value = "获取福利编号-pc",position=3)
    @PostMapping(value = "/getNumber")
    public HttpResult<Map> getNumber(
            @ApiParam(name = "type", value = "福利类型 1红包2代金卷3金星翻倍卡4福利套餐", required = true) @RequestParam( required = true) int type) {
        String res="";
        String equipmentNo="";
        Map resultMap = new HashMap();
        switch (type){
            case 1:
                List<String> orderby =new ArrayList<String>();
                orderby.add("create_time");
                EntityWrapper<RedBags> wrapper = new EntityWrapper();
                wrapper.orderDesc(orderby).last("limit 1");
                List<RedBags> list = redBagsService.selectList(wrapper);
                if(list.size()==0){
                    equipmentNo = StringUtil.getNewEquipmentNo(CommonConstants.WELFARE_REDBAGS, "0");
                }else{
                    res = StringUtil.getEquipmentNo(CommonConstants.WELFARE_REDBAGS,list.get(0).getNumber());
                    equipmentNo = StringUtil.getNewEquipmentNo(CommonConstants.WELFARE_REDBAGS, res);
                }
                break ;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
        }
        resultMap.put("number",equipmentNo);
        return  HttpResult.success(resultMap);
    }
    @ApiOperation(value="创建红包福利-pc", notes="创建红包福利", produces="application/json",position=1)
    @PostMapping("/saveRedBags")
    public HttpResult<Map> saveTravel(@ApiParam(name="传入对象",value="传入json格式",required=true) @RequestBody @Valid RedBags redBags, @ApiIgnore @RequestHeader(value = "uid") String uid) {
        if(StringUtil.isNull(uid)){
            return HttpResult.failure(ErrCodeAndMsg.REQ_PARAM_ERROR);
        }
        if(redBags.getGrantTermType()==1){
            if(redBags.getGrantTermStart()==null || redBags.getGrantTermEnd()==null){
                return HttpResult.failure(ErrCodeAndMsg.REQ_PARAM_ERROR);
            }
        }
        if(redBags.getUseTermType()==1){
            if(redBags.getUseTermStart()==null || redBags.getUseTermEnd()==null){
                return HttpResult.failure(ErrCodeAndMsg.REQ_PARAM_ERROR);
            }
        }
        if(redBags.getUseTermType()==0){
            if(redBags.getUseTermDay()==null ){
                return HttpResult.failure(ErrCodeAndMsg.REQ_PARAM_ERROR);
            }
        }
        redBags.setCreateUserId(Long.parseLong(uid));
        redBags.setStatus(1);
        try {
            redBagsService.insert(redBags);
        }catch (Exception ex){
            if(ex.getCause() instanceof MySQLIntegrityConstraintViolationException){
                return HttpResult.failure("福利编号不能重复");
            }
        }
        Map resultMap = new HashMap();
        if(redBags.getId()!=null){
            resultMap.put("id",redBags.getId());
        }else{
            return HttpResult.failure(ErrCodeAndMsg.JUST_MOMENT_ERROR);
        }
        return HttpResult.success(resultMap);
    }
}
