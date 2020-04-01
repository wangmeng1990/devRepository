package com.yinuo.bigdata.system.controller;

import com.inuol.bigdata.TBigdataCommunication;
import com.inuol.bigdata.ext.TBigdataCommunicationNode;
import com.inuol.user.Roles;
import com.inuol.user.User;
import com.yinuo.api.user.UserApi;
import com.yinuo.bigdata.system.service.CommunicationService;
import com.yinuo.bigdata.system.service.PersonalService;
import com.yinuo.common.common.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author ：jias
 * @date ：2020/1/7 16:10
 */
@RestController
@Api(value = "app",description = "APP我的模块接口")
public class AppSystemController {

    @Autowired
    private PersonalService personalService;

    @Autowired
    private CommunicationService communicationService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserApi userApi;

    private List<Roles> rolesList; // 部门集合

    /**
     * 初始化数据
     */
    @PostConstruct
    public void init(){
        this.rolesList = (List<Roles>)redisTemplate.boundHashOps("rolesList").get("rolesList");
    }


    @PostMapping("/appSelectUserById")
    @ApiOperation("查询用户基本信息")
    public HttpResult selectUserById(@RequestHeader(value = "uid") String uid){
        User user = personalService.selectUserById(Long.valueOf(uid));
        if (user!=null) {
            return HttpResult.success(user);
        }
        return HttpResult.success(null);
    }


    @PostMapping(value = "/appUpdateUserById")
    @ApiOperation("修改用户基本信息")
    public HttpResult appUpdateUserById(@RequestHeader(value = "uid") String uid, @RequestBody User user){
           user.setId(Long.valueOf(uid));
           int i = personalService.updateUser(user);
           if (i>0) {
               return HttpResult.success("修改成功！！！");
           }
           return HttpResult.failure("修改失败！！！");
    }


    @GetMapping("/appSelectCommunicate")
    @ApiOperation("查询通讯录部门和部门人数")
    public HttpResult appSelectCommunicate(){
        List<TBigdataCommunicationNode> list = communicationService.appSelectCommunicate();
        for (int i = 0; i < list.size() ; i++) {
            List<TBigdataCommunication> children = list.get(i).getChildren();
            for (int j = 0; j < children.size(); j++) {
                children.get(j).setRoleId(getRolesId(children.get(j).getRoleId()));
            }
            list.get(i).setCut(list.get(i).getChildren().size());
        }
        return HttpResult.success(list);
    }

    /**
     * 根据id查询角色信息
     * @param rolesId
     * @return
     */
    public String getRolesId(String rolesId){
        for (Roles roles:rolesList) {
            if (roles.getId().toString().equals(rolesId)){
                return roles.getName();
            }
        }
        rolesList = (List<Roles>)redisTemplate.boundHashOps("rolesList").get("rolesList");
        Roles roles = userApi.getRolesById(Long.valueOf(rolesId));
        if (roles != null){
            return  roles.getName();
        }
        return null;
    }

}
