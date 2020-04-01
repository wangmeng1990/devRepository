package com.yinuo.backstageAuth.service;

import com.yinuo.common.common.HttpResult;
import com.inuol.vo.backstageAuth.UserRoleVo;
import com.inuol.vo.backstageAuth.UserVo;


/**
 * @author Capejor
 * @date 2020-01-08 15:19
 */
public interface UserService {

    HttpResult insertUser(UserVo vo, UserRoleVo userRoleVo);

    HttpResult updateUser(UserVo vo, UserRoleVo userRoleVo);

    HttpResult selectAllUser(Integer pageNum, Integer pageSize, String param, String startTime, String endTime);

    HttpResult  deleteOneById(Long id);

    int selectUserCountByOrgId(Long id);

    HttpResult resetPassword(Long id);

    HttpResult selectUserById(Long id);

    HttpResult selectUserByPhone(String phone);
}
