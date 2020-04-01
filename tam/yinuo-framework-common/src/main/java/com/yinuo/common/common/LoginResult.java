package com.yinuo.common.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：jias
 * @date ：2020/1/14 15:33
 */
@Data
@ApiModel("登录返回对象")
public class LoginResult {

    /**
     * 成功标示
     */
    @ApiModelProperty("是否请求成功")
    private boolean success;

    /**
     * 用户类型
     */
    @ApiModelProperty("用户类型")
    private String userType;

    /**
     * token
     */
    @ApiModelProperty("token")
    private String token;

    /**
     * APP端角色权限
     */
    @ApiModelProperty("APP端角色权限")
    private String appAuth;


    /**
     * 提示信息
     */
    @ApiModelProperty("提示信息")
    private String message;

    public LoginResult(boolean success, String userType, String token, String appAuth, String message) {
        this.success = success;
        this.userType = userType;
        this.token = token;
        this.appAuth = appAuth;
        this.message = message;
    }
}
