package com.inuol.vo.backstageAuth;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * @author Capejor
 * @date 2020-01-13 17:01
 */
@Data
@ApiModel("角色对象")
@TableName("t_role")
public class RoleVo {

    @ApiModelProperty(value = "id",required = false)
    private Long id;

    @ApiModelProperty(value = "角色编码",required = false)
    private String roleCode;

    @ApiModelProperty(value = "角色名称",required = false)
    private String roleName;

    @ApiModelProperty(value = "备注",required = false)
    private String remark;

}
