package com.yinuo.auth.pojo;

import lombok.Data;

/**
 * 登录参数实体类
 * @author ：jias
 * @date ：2020/1/13 10:49
 */
@Data
public class LoginRequest {

    private String username; // 用户名

    private String password; // 密码

    private String terminal; //登录终端

}
