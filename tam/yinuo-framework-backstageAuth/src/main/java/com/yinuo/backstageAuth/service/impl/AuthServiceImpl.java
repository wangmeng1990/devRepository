package com.yinuo.backstageAuth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inuol.entity.backstageAuth.User;
import com.yinuo.backstageAuth.mapper.AuthMapper;
import com.yinuo.backstageAuth.service.AuthService;
import com.yinuo.common.utils.fileUpload.PasswordHelper;

/**
 * @author Capejor
 * @date 2020-01-20 13:34
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    public User accredit(String userName, String password) {
        User user = authMapper.selectUserByUserName(userName);
        // 进行MD5加密
        PasswordHelper passwordHelper = new PasswordHelper();
        String password_md5_sale = passwordHelper.encryptPassword(password, user.getSalt());
        if (password_md5_sale.equals(user.getPassword())){
            return user;
        }
        return null;
    }



}
