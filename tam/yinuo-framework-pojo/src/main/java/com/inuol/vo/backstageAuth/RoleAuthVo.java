package com.inuol.vo.backstageAuth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Capejor
 * @date 2020-01-13 17:12
 */
@Data
@ApiModel("角色权限对象")
public class RoleAuthVo {

    @ApiModelProperty(value = "id",required = false)
    private Long id;

    @ApiModelProperty(value = "角色id",required = false)
    private Long roleId;

    @ApiModelProperty(value = "权限id",required = false)
    private Long authId;
}
