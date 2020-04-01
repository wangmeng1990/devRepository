package com.yinuo.backstageAuth.service.impl;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.inuol.entity.backstageAuth.Role;
import com.inuol.entity.backstageAuth.RoleAuth;
import com.yinuo.backstageAuth.mapper.AuthMapper;
import com.yinuo.backstageAuth.mapper.RoleAuthMapper;
import com.yinuo.backstageAuth.mapper.RoleMapper;
import com.yinuo.common.common.HttpResult;
import com.inuol.vo.backstageAuth.RoleVo;
import com.yinuo.backstageAuth.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

/**
 * @author Capejor
 * @date 2020-01-10 16:38
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private RoleAuthMapper roleAuthMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult insertRole(RoleVo vo, Long[] authId) {
        Role role = new Role();
        BeanUtils.copyProperties(vo, role);
        role.setId(IdWorker.getId());
        role.setCreateTime(new Date());
        role.setCreator(1L);

        int result;
        result = roleMapper.insertSelective(role);
        if (result == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return HttpResult.retCol(-1);
        }

        for (int i = 0; i < authId.length; i++) {
            RoleAuth roleAuth = new RoleAuth();
            roleAuth.setId(IdWorker.getId());
            roleAuth.setRoleId(role.getId());
            roleAuth.setAuthId(authId[i]);
            result = roleAuthMapper.insertSelective(roleAuth);
            if (result == 0) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return HttpResult.retCol(-1);
            }
        }
        //result = roleAuthMapper.insertBatch();
        return HttpResult.retCol(result);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult updateRole(RoleVo vo,Long[] authId) {
        Role role = new Role();
        BeanUtils.copyProperties(vo, role);
        role.setUpdateTime(new Date());
        int result;
        result = roleMapper.updateByPrimaryKeySelective(role);
        if (result == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return HttpResult.retCol(-1);
        }

        result = roleAuthMapper.deleteByRoleId(role.getId());
        if (result == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return HttpResult.retCol(-1);
        }

        for (int i = 0; i < authId.length; i++) {
            RoleAuth roleAuth = new RoleAuth();
            roleAuth.setId(IdWorker.getId());
            roleAuth.setRoleId(role.getId());
            roleAuth.setAuthId(authId[i]);
            result = roleAuthMapper.insertSelective(roleAuth);
            if (result == 0) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return HttpResult.retCol(-1);
            }
        }
        return HttpResult.retCol(result);
    }

    @Override
    public HttpResult selectAllRole(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> list = roleMapper.selectAllRole();
        PageInfo<Role> pageInfo = new PageInfo<>(list);
        return HttpResult.success(pageInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult deleteOneById(Long id) {
        int result;
        result = roleMapper.deleteByPrimaryKey(id);
        if (result == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return HttpResult.retCol(-1);
        }
        result = roleAuthMapper.deleteByRoleId(id);
        if (result == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return HttpResult.retCol(-1);
        }
        return HttpResult.retCol(result);
    }

    @Override
    public HttpResult selectRoleAuthByRoleId(String roleId) {
        List<Map<String, Object>> childList = new ArrayList<>();
        List<Long> authIdList = roleAuthMapper.selectAuthIdByRoleId(roleId);
        //查询顶级
        List<Map<String, Object>> topList = authMapper.selectTopAuth();
        if (topList != null && topList.size() > 0) {
            for (Map<String, Object> topMap : topList) {
                Map<String, Object> childMap = new HashMap<>();
                childMap.put("id", topMap.get("id"));
                childMap.put("label", topMap.get("authText"));
                childMap.put("parentId", topMap.get("parentId"));
                setAuthTree(childMap,authIdList);
                childList.add(childMap);
            }
        }
        return HttpResult.success(childList);
    }

    //递归
    public void setAuthTree(Map<String, Object> map,List<Long> authIdList) {
        List<Map<String, Object>> childList = new ArrayList<>();
        map.put("children", childList);
        String parentId = map.get("id").toString();
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("parentId" ,parentId);
        paramMap.put("authIdList",authIdList);
        List<Map<String, Object>> authList = authMapper.selectAuthByParentId(paramMap);
        if (authList != null && authList.size() > 0) {
            for (Map<String, Object> authMap : authList) {
                Map<String, Object> childMap = new HashMap<>();
                childMap.put("id", authMap.get("id"));
                childMap.put("label", authMap.get("authText"));
                childMap.put("parentId", authMap.get("parentId"));
                setAuthTree(childMap,authIdList);
                childList.add(childMap);
            }
        }
    }

    @Override
    public HttpResult selectRoleAuth() {
        return null;
    }
}
