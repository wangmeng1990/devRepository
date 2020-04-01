package com.yinuo.backstageAuth.controller;

import com.inuol.entity.backstageAuth.User;
import com.yinuo.backstageAuth.config.JwtProperties;
import com.yinuo.backstageAuth.model.Login;
import com.yinuo.backstageAuth.service.AuthService;
import com.yinuo.common.common.HttpResult;
import com.yinuo.common.pojo.UserInfo;
import com.yinuo.common.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Capejor
 * @date 2020-01-13 10:48
 */
@Api(tags = "登录")
@RestController
@RequestMapping("/backstageLogin")
@EnableConfigurationProperties(JwtProperties.class)
public class LoginController {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public HttpResult accredit(@RequestBody Login login, HttpServletRequest request, HttpServletResponse response) {
        User user = authService.accredit(login.getUserName(), login.getPassword());
        /*if (StringUtils.isBlank(token)){
            return null;
        }*/
        if (user == null) {
            return HttpResult.failure("用户或密码错误！！！");
        }
        try {
            // 3.jwtUtils生成jwt类型的token
            UserInfo userInfo = new UserInfo();
            userInfo.setId(user.getId());
            userInfo.setUsername(user.getUserName());
            String token = JwtUtils.generateToken(userInfo, this.jwtProperties.getPrivateKey(), this.jwtProperties.getExpire());
            if (StringUtils.isBlank(token)) {
                return HttpResult.failure("服务器内部错误！！！");
            }
            //CookieUtils.setCookie(request, response, this.jwtProperties.getCookieName(), token, this.jwtProperties.getExpire() * 60);
            /*CookieUtil.addCookie(response,"localhost","/",this.jwtProperties.getCookieName(),
                    token,this.jwtProperties.getExpire() * 60,false);*/
            Map<String, String> map = new HashMap<>();
            map.put("token", token);
            map.put("appAuth", "1");
            return HttpResult.success(map);
            //LoginResult(true,user.getUserType().toString(),token,"","登录成功！！！");
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.failure("服务器内部错误！！！");
        }
    }


}
