package com.yinuo.backstageAuth.service.impl;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.inuol.entity.backstageAuth.User;
import com.inuol.entity.backstageAuth.UserRole;
import com.yinuo.backstageAuth.mapper.UserMapper;
import com.yinuo.backstageAuth.mapper.UserRoleMapper;
import com.yinuo.backstageAuth.service.UserService;
import com.yinuo.common.common.HttpResult;
import com.yinuo.common.utils.fileUpload.CommUtil;
import com.yinuo.common.utils.fileUpload.PasswordHelper;
import com.inuol.vo.backstageAuth.UserRoleVo;
import com.inuol.vo.backstageAuth.UserVo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Capejor
 * @date 2020-01-08 15:19
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult insertUser(UserVo vo, UserRoleVo userRoleVo) {
        User user = new User();
        // 获取盐值
        String salt = CommUtil.randomString(6);
        // 进行MD5加密
        PasswordHelper passwordHelper = new PasswordHelper();
        String password_md5_sale = passwordHelper.encryptPassword("123456", salt);
        BeanUtils.copyProperties(vo, user);
        user.setId(IdWorker.getId());
        user.setPassword(password_md5_sale);
        user.setSalt(salt);
        user.setCreateTime(new Date());
        user.setCreator(1L);
        int result;
        result = userMapper.insertSelective(user);
        if (result == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return HttpResult.retCol(-1);
        }

        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(userRoleVo, userRole);

        userRole.setUserId(user.getId());
        result = userRoleMapper.insertSelective(userRole);
        if (result == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return HttpResult.retCol(-1);
        }
        return HttpResult.retCol(result);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult updateUser(UserVo vo, UserRoleVo userRoleVo) {
        User user = new User();
        BeanUtils.copyProperties(vo, user);
        user.setUpdateTime(new Date());

        // 修改用户
        int result;
        result = userMapper.updateByPrimaryKeySelective(user);
        if (result == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return HttpResult.retCol(-1);
        }

        //删除用户角色
        result = userRoleMapper.deleteByUserId(vo.getId());
        if (result == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return HttpResult.retCol(-1);
        }
        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(userRoleVo, userRole);
        userRole.setId(IdWorker.getId());
        userRole.setUserId(user.getId());
        result = userRoleMapper.insertSelective(userRole);
        if (result == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return HttpResult.retCol(-1);
        }
        return HttpResult.retCol(result);

    }

    @Override
    public HttpResult selectAllUser(Integer pageNum, Integer pageSize, String param, String startTime, String endTime) {
        PageHelper.startPage(pageNum, pageSize);
//        UserCriteria userCriteria = new UserCriteria();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            Date startDate = sdf.parse(startTime);
//            Date endDate = sdf.parse(endTime);
//            userCriteria.createCriteria().andCreateTimeBetween(startDate,endDate).a;
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        Map<String, Object> map = new HashMap<>();
        map.put("param", param);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        List<User> list = userMapper.selectAllUser(map);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return HttpResult.success(pageInfo);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult deleteOneById(Long id) {
        int result;
        result = userMapper.deleteByPrimaryKey(id);
        if (result == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return HttpResult.retCol(-1);
        }
        result = userRoleMapper.deleteByUserId(id);
        if (result == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return HttpResult.retCol(-1);
        }
        return HttpResult.retCol(result);
    }

    @Override
    public int selectUserCountByOrgId(Long id) {
        return userMapper.selectUserCountByOrgId(id);
    }

    @Override
    public HttpResult resetPassword(Long id) {
        User user = new User();
        // 获取盐值
        String salt = CommUtil.randomString(6);
        // 进行MD5加密
        PasswordHelper passwordHelper = new PasswordHelper();
        String password_md5_sale = passwordHelper.encryptPassword("88888888", salt);
        user.setId(id);
        user.setPassword(password_md5_sale);
        user.setSalt(salt);
        return HttpResult.retCol(userMapper.updateByPrimaryKeySelective(user));
    }

    @Override
    public HttpResult selectUserById(Long id) {
        return HttpResult.success(userMapper.selectByPrimaryKey(id));
    }

    @Override
    public HttpResult selectUserByPhone(String phone) {
        return HttpResult.success(userMapper.selectUserByPhone(phone));
    }
}
