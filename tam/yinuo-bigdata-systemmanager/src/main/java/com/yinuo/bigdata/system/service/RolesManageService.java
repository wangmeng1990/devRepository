package com.yinuo.bigdata.system.service;

import com.github.pagehelper.Page;
import com.inuol.user.Roles;
import com.inuol.user.User;
import com.yinuo.api.user.UserApi;

import com.yinuo.common.common.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理端权限管理模块Service
 * @author ：jias
 * @date ：2020/1/9 15:53
 */
@Service
public class RolesManageService {

    @Autowired
    private UserApi userApi;

    /**
     * 查询所有角色
     * @param page
     * @param size
     * @return
     */
    public Page<Roles> findAllRole(int page, int size){
        return userApi.findAllRoles(page, size);
    }

    /**
     * 新增角色
     * @param roles
     * @return
     */
    public HttpResult addRole(Roles roles){
        return userApi.addRoles(roles);
    }

    /**\
     * 查询所有角色
     * @return
     */
    public List<Roles> findRoles(){
        return userApi.findRoles();
    }









}
