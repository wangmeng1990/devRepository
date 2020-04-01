package com.yinuo.common.pojo;

import java.io.Serializable;

/**
 * 载荷对象
 */
public class UserInfo implements Serializable {

    private Long id;

    private String username;

    private String roleId;

    private String terminal; //登录端

    private String userType; //用户类型

    public UserInfo() {
    }

    public UserInfo(Long id, String username, String roleId, String terminal, String userType) {
        this.id = id;
        this.username = username;
        this.roleId = roleId;
        this.terminal = terminal;
        this.userType = userType;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}