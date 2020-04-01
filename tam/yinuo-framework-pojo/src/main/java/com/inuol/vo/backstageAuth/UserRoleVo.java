package com.inuol.vo.backstageAuth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Capejor
 * @date 2020-01-13 11:13
 */
@Data
@ApiModel("用户角色对象")
public class UserRoleVo {

    @ApiModelProperty(value = "用户角色id",required = false)
    private Long id;

    @ApiModelProperty(value = "用户id",required = false)
    private Long userId;

    @ApiModelProperty(value = "角色id",required = true)
    private Long roleId;
}

