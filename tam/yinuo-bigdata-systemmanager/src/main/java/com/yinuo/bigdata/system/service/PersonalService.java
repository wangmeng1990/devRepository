package com.yinuo.bigdata.system.service;

import com.inuol.user.User;
import com.yinuo.api.user.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 个人中心
 * @author ：jias
 * @date ：2020/1/14 19:06
 */
@Service
public class PersonalService {

    @Autowired
    private UserApi userApi;

    public User selectUserById(Long id){
        return userApi.getUserById(id);
    }

    public int updateUser(User user){
        return userApi.updateUset(user);
    }

    public int updateUserPassword(Long id,String password,String newpassword){
          return userApi.updatePassword(id, password, newpassword);
    }


}
