package com.yinuo.api.backstageUser;

import com.yinuo.api.user.UserApiFallback;
import com.yinuo.common.common.HttpResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Capejor
 * @date 2020-01-21 15:56
 */
@FeignClient(value = "user",fallback = UserApiFallback.class)
public interface BackStageUserApi {


    @ApiOperation("根据id查询用户")
    @GetMapping("/selectUserById")
    HttpResult selectUserById(@ApiParam(name = "id", value = "id") @RequestParam Long id);

    @ApiOperation("根据手机查询用户")
    @GetMapping("/selectUserByPhone")
    HttpResult selectUserByPhone(@ApiParam(name = "phone", value = "phone") @RequestParam String phone);
}
