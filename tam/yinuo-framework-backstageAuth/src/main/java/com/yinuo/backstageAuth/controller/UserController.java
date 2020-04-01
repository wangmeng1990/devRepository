package com.yinuo.backstageAuth.controller;

import com.yinuo.backstageAuth.service.UserService;
import com.yinuo.common.common.HttpResult;
import com.inuol.vo.backstageAuth.UserRoleVo;
import com.inuol.vo.backstageAuth.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Capejor
 * @date 2020-01-08 14:58
 */
@Api(tags = "用户管理")
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("添加用户")
    @PostMapping("/insertUser")
    public HttpResult insertUser(UserVo vo, UserRoleVo userRoleVo) {
        return userService.insertUser(vo, userRoleVo);
    }

    @ApiOperation("编辑用户")
    @PostMapping("/updateUser")
    public HttpResult updateUser(UserVo vo, UserRoleVo userRoleVo) {
        return userService.updateUser(vo,userRoleVo);
    }

    @ApiOperation("查询所有用户")
    @GetMapping("/selectAllUser")
    public HttpResult selectAllUser(@ApiParam(name = "pageNum", value = "页数") @RequestParam Integer pageNum,
                                @ApiParam(name = "pageSize", value = "每页条数") @RequestParam Integer pageSize,
                                @ApiParam(name = "param", value = "姓名、用户名、手机") @RequestParam(required = false) String param,
                                @ApiParam(name = "startTime", value = "开始时间") @RequestParam(required = false) String startTime,
                                @ApiParam(name = "endTime", value = "结束时间") @RequestParam(required = false) String endTime) {
        return userService.selectAllUser(pageNum, pageSize, param, startTime, endTime);
    }

    @ApiOperation("删除用户")
    @PostMapping("/deleteUser")
    public HttpResult deleteUser(@ApiParam(name = "id", value = "id") @RequestParam Long id) {
        return userService.deleteOneById(id);
    }

    @ApiOperation("重置密码")
    @PostMapping("/resetPassword")
    public HttpResult resetPassword(Long id){
        return userService.resetPassword(id);
    }

    @ApiOperation("根据id查询用户")
    @GetMapping("/selectUserById")
    public HttpResult selectUserById(@ApiParam(name = "id", value = "id") @RequestParam Long id) {
        return userService.selectUserById(id);
    }

    @ApiOperation("根据手机查询用户")
    @GetMapping("/selectUserByPhone")
    public HttpResult selectUserByPhone(@ApiParam(name = "phone", value = "phone") @RequestParam String phone) {
        return userService.selectUserByPhone(phone);
    }

}
