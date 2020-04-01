package com.yinuo.auth.controller;


import com.inuol.user.User;
import com.yinuo.auth.config.JwtProperties;
import com.yinuo.auth.pojo.LoginRequest;
import com.yinuo.auth.pojo.Register;
import com.yinuo.auth.service.AuthService;
import com.yinuo.common.common.ErrCodeAndMsg;
import com.yinuo.common.common.HttpResult;
import com.yinuo.common.common.LoginResult;
import com.yinuo.common.pojo.UserInfo;
import com.yinuo.common.utils.JwtUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@RestController
@EnableConfigurationProperties(JwtProperties.class)
@Api(tags="注册登录管理")
public class AuthController {

    private  final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private HttpServletRequest request;

    private static final String codeKey = "code";

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public HttpResult accredit(@RequestBody LoginRequest login
            /*@RequestParam("username")String username,
            @RequestParam("password")String password,*/
            /*HttpServletRequest request,
                                HttpServletResponse response*/
    ){
        User user = this.authService.accredit(login.getUsername(), login.getPassword());
        if (user == null){
            return HttpResult.failure(ErrCodeAndMsg.LOGIN_PWD_ERROR);
        }
        try {
            // 3.jwtUtils生成jwt类型的token
            UserInfo userInfo = new UserInfo();
            userInfo.setId(user.getId());
            userInfo.setUsername(user.getUsername());
            if (user.getRoleId()!=null) {
                userInfo.setRoleId(user.getRoleId().toString());
            }
            userInfo.setTerminal(login.getTerminal());
            userInfo.setUserType(user.getUserType().toString());
            String token = "";
            logger.info("用户"+login.getUsername()+"登录终端为："+login.getTerminal());
            if (login.getTerminal().equals("app")) {
                token = JwtUtils.generateToken(userInfo, this.jwtProperties.getPrivateKey(), 60*24*7); //设置jwt过期时间为一周
            } else {
                token = JwtUtils.generateToken(userInfo, this.jwtProperties.getPrivateKey(), this.jwtProperties.getExpire());
            }
            if (StringUtils.isBlank(token)){
                return HttpResult.failure("服务器内部错误！！！");
            }
            //CookieUtils.setCookie(request, response, this.jwtProperties.getCookieName(), token, this.jwtProperties.getExpire() * 60);
            /*CookieUtil.addCookie(response,"localhost","/",this.jwtProperties.getCookieName(),
                    token,this.jwtProperties.getExpire() * 60,false);*/
            Map<String,String> map = new HashMap<>();
            map.put("userType",user.getUserType().toString());
            map.put("token",token);
            map.put("appAuth","1");
            return HttpResult.success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.failure("服务器内部错误！！！");
        }

    }


    @PostMapping("/registerUser")
    @ApiOperation(value = "APP用户注册")
    public HttpResult registerUser(/*@RequestParam("phone") String phone,
                               @RequestParam("password") String password,
                               @RequestParam("code") String cod*/
           @RequestBody Register register){
        String codeValue = stringRedisTemplate.opsForValue().get(codeKey+register.getUsername());
        if (StringUtils.isBlank(codeValue)){
            return HttpResult.failure(ErrCodeAndMsg.SMSCODE_ERROR);
        }
        if (!codeValue.equals(register.getCode())){
          return HttpResult.failure(ErrCodeAndMsg.SMSCODE_ERROR);
        }
        if (authService.getUserByName(register.getUsername()) != null){
          return HttpResult.failure(ErrCodeAndMsg.EMPLOYEE_MOBILE_ERROR);
        }
        User user = new User();
        user.setUsername(register.getUsername());
        user.setPassword(register.getPassword());
        user.setUserType(1);
        user.setState(1);
        return  this.authService.registerUser(user);
    }

    @GetMapping("/getCode")
    @ApiOperation(value = "获取验证码")
    public HttpResult getCode(@RequestParam("phone") String phone){
        String code = createCode(phone);
        if (code != null) {
            return HttpResult.success(code);
        }
        return HttpResult.failure("获取验证码失败！！！");


    }

    /**
     * 生成6位数验证码
     * @return
     */
    public String createCode(String phone){
        Random random = new Random();
        Double result = random.nextDouble();
        String str = result.toString();
        if (StringUtils.isBlank(str)){
            return null;
        }
        String substr = str.substring(str.length() - 6);
        stringRedisTemplate.boundValueOps(codeKey+phone).set(substr,5, TimeUnit.MINUTES);
        return substr;
    }


    /*@GetMapping("accredit")
    public ResponseEntity<Void> accredit(
            @RequestParam("username")String username,
            @RequestParam("password")String password,
            HttpServletRequest request,
            HttpServletResponse response
            ){
        String token = this.authService.accredit(username, password);

        if (StringUtils.isBlank(token)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        CookieUtils.setCookie(request, response, this.jwtProperties.getCookieName(), token, this.jwtProperties.getExpire() * 60);
        System.out.println("=================================================");
        return ResponseEntity.ok(null);
    }*/

}
