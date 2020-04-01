package com.yinuo.auth.pojo;

import lombok.Data;

/**
 * app注册实体类
 * @author ：jias
 * @date ：2020/1/13 10:49
 */
@Data
public class Register {

    private String username;// 用户名

    private String password;// 密码

    private String code; //验证码

}
