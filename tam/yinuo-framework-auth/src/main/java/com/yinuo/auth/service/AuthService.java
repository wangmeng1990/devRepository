package com.yinuo.auth.service;


import com.inuol.user.User;
import com.yinuo.auth.client.UserClient;

import com.yinuo.common.common.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserClient userClient;

    public User accredit(String username, String password) {
        // 1.根据用户名和密码查询
        User user = this.userClient.queryUser(username, password);
        // 2.判断user
        if (user == null) {
            return null;
        }
        /*try {
            // 3.jwtUtils生成jwt类型的token
            UserInfo userInfo = new UserInfo();
            userInfo.setId(user.getId());
            userInfo.setUsername(user.getUsername());
            userInfo.setRoleId(user.getRoleId());
            return JwtUtils.generateToken(userInfo, this.jwtProperties.getPrivateKey(), this.jwtProperties.getExpire());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return user;
    }

    public HttpResult registerUser(User user) {
        return this.userClient.register(user);
    }

    public User getUserByName(String username){
        return userClient.getUserByName(username);
    }

}
