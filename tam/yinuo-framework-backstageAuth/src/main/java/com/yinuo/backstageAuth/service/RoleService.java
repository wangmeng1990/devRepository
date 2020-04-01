package com.yinuo.backstageAuth.service;

import com.yinuo.common.common.HttpResult;
import com.inuol.vo.backstageAuth.RoleAuthVo;
import com.inuol.vo.backstageAuth.RoleVo;

import java.util.List;

/**
 * @author Capejor
 * @date 2020-01-10 16:38
 */
public interface RoleService {

    HttpResult insertRole(RoleVo vo,Long[] authId);

    HttpResult updateRole(RoleVo vo, Long[] authId);

    HttpResult selectAllRole(Integer pageNum,Integer pageSize);

    HttpResult deleteOneById(Long id);

    HttpResult selectRoleAuthByRoleId(String roleId);

    HttpResult selectRoleAuth();
}
