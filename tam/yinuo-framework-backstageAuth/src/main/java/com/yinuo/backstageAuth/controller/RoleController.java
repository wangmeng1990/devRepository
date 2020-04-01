package com.yinuo.backstageAuth.controller;

import com.yinuo.common.common.HttpResult;
import com.inuol.vo.backstageAuth.RoleVo;
import com.yinuo.backstageAuth.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Capejor
 * @date 2020-01-10 16:37
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("新增角色")
    @PostMapping("/insertRole")
    public HttpResult insertRole(@Validated @RequestBody RoleVo vo,@RequestParam(value = "authId") Long[] authId) {
        return roleService.insertRole(vo,authId);
    }


    @ApiOperation("编辑角色")
    @PostMapping("/updateRole")
    public HttpResult updateRole(@Validated @RequestBody RoleVo vo, @RequestParam(value = "authId") Long[] authId) {
        return roleService.updateRole(vo, authId);
    }


    @ApiOperation("查询所有角色")
    @GetMapping("/selectAllRole")
    public HttpResult selectAllRole(@ApiParam(name = "pageNum", value = "页数") @RequestParam Integer pageNum,
                                    @ApiParam(name = "pageSize", value = "每页条数") @RequestParam Integer pageSize) {
        return roleService.selectAllRole(pageNum, pageSize);
    }

    @ApiOperation("删除角色")
    @PostMapping("/deleteRole")
    public HttpResult deleteRole(@ApiParam(name = "id", value = "id") @RequestParam Long id) {
        return roleService.deleteOneById(id);
    }


    @ApiOperation("查询当前角色的权限")
    @PostMapping("/selectRoleAuthByRoleId")
    public HttpResult selectRoleAuthByRoleId(@ApiParam(name = "角色id", value = "roleId")
                                              @RequestParam(value = "roleId") String roleId) {
        return roleService.selectRoleAuthByRoleId(roleId);
    }


//    @ApiOperation("查询角色权限")
//    @GetMapping("/selectRoleAuth")
//    public HttpResult selectRoleAuth() {
//        return roleService.selectRoleAuth();
//    }

}
