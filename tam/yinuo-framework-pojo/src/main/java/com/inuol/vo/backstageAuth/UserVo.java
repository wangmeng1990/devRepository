package com.inuol.vo.backstageAuth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author Capejor
 * @date 2020-01-08 15:45
 */
@Data
@ApiModel("用户对象")
public class UserVo {

    @ApiModelProperty(value = "id",required = false)
    private Long id;

    @ApiModelProperty(value = "组织id",required = false)
    private Long orgId;

    @ApiModelProperty(value = "真实姓名",required = false)
    private String realName;

    @ApiModelProperty(value = "电话",required = false)
    private String phone;

    @ApiModelProperty(value = "QQ号",required = false)
    private String qq;

    @ApiModelProperty(value = "微信号",required = false)
    private String weChat;

    @ApiModelProperty(value = "电子邮箱",required = false)
    private String email;

    @ApiModelProperty(value = "身份证号",required = false)
    private String idCard;

    @ApiModelProperty(value = "性别",required = false)
    private String sex;

    @ApiModelProperty(value = "用户名",required = false)
    private String userName;

    @ApiModelProperty(value = "盐值",required = false)
    private String salt;

    @ApiModelProperty(value = "密码",required = false)
    private String password;

    @ApiModelProperty(value = "启用状态：1启用，0禁用",required = false)
    private String state;

//    @ApiModelProperty(value = "用户角色",required = true)
//    private UserRoleVo userRoleVo;




}
