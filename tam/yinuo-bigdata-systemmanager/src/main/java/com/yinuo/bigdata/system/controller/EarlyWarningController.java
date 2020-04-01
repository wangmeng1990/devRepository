package com.yinuo.bigdata.system.controller;

import com.inuol.bigdata.BigdataWarning;
import com.inuol.bigdata.TBigdataCommunication;
import com.inuol.bigdata.ext.TBigdataCommunicationNode;
import com.inuol.user.Roles;
import com.inuol.user.User;
import com.yinuo.api.user.UserApi;
import com.yinuo.bigdata.system.service.CommunicationService;
import com.yinuo.bigdata.system.service.PersonalService;
import com.yinuo.bigdata.system.service.WarningService;
import com.yinuo.common.common.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author ：jias
 * @date ：2020/1/22 15:10
 */
@RestController
@Api(value = "智能预警",description = "智能预警接口")
public class EarlyWarningController {

    @Autowired
    private WarningService warningService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/reported")
    @ApiOperation("应急救助上报")
     public HttpResult reportedEmergency(@RequestHeader(value = "uid") String uid, @RequestBody BigdataWarning bigdataWarning){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String key = "reported:"+sdf.format(new Date());
        int num = 1;
        //查询redis是否存在此key
        if (stringRedisTemplate.hasKey(key)) {
            //如果存在则获取当前value值然后+1
            String i = stringRedisTemplate.opsForValue().get(key);
            num = Integer.valueOf(i)+1;
        } else {
            stringRedisTemplate.boundValueOps(key).set(num+"");
        }
        bigdataWarning.setWarningNumber("YJJZ"+key.split(":")[1]+num);
        int reported = warningService.reported(uid,bigdataWarning);
        if (reported>0){
            return  HttpResult.success("上报成功！！！");
        }
        return HttpResult.failure("上报失败！！！");
     }

















}
