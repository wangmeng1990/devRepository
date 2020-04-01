package com.yinuo.backstageAuth.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author Capejor
 * @date 2020-01-19 14:26
 */
@Data
@ApiModel("登录对象")
public class Login {

    private String userName; // 用户名

    private String password; // 密码

}
