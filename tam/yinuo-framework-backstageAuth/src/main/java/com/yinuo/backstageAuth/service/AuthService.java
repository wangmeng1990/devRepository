package com.yinuo.backstageAuth.service;

import com.inuol.entity.backstageAuth.User;

/**
 * @author Capejor
 * @date 2020-01-20 13:33
 */
public interface AuthService {

    User accredit(String userName, String password);
}
