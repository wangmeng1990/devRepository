package com.yinuo.user.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.inuol.user.Roles;
import com.inuol.user.Urls;
import com.inuol.user.User;
import com.inuol.user.criteria.CriteriaUser;
import com.yinuo.common.common.HttpResult;
import com.yinuo.user.service.RolesService;
import com.yinuo.user.service.UrlsService;
import com.yinuo.user.service.UserService;


/**
 * 用户和权限接口
 */
@RestController
public class UserAndAuthController {

    /*@Autowired
    private StringRedisTemplate stringRedisTemplate;*/
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private UrlsService urlsService;

    @Autowired
    private RolesService rolesService;

    private static final String key = "tiananmen";

    private static final String authKeyList = "tamauthLsit";

    @GetMapping("/check/{data}/{type}")
    public ResponseEntity<Boolean> checkUser(@PathVariable("data")String data, @PathVariable("type")Integer type){
        Boolean bool = this.userService.checkUser(data, type);
        if (bool == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    /*@PostMapping("code")
    public ResponseEntity<Void> sendVerifyCode(@RequestParam("phone")String phone){
        this.userService.sendVerifyCode(phone);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }*/

    @PostMapping("/register")
    public HttpResult register(@Valid @RequestBody User user, BindingResult bindingResult){
        try {
            if (bindingResult.getFieldErrorCount() > 0){
                StringBuffer str = new StringBuffer();
                List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            /*for (FieldError fieldError : fieldErrors) {
               str.append(fieldError.getDefaultMessage()+";");
            }*/
                for (int i = 0; i < fieldErrors.size(); i++) {
                    if (i != fieldErrors.size()-1) {
                        str.append(fieldErrors.get(i).getDefaultMessage()+";");
                    } else {
                        str.append(fieldErrors.get(i).getDefaultMessage());
                    }
                }
            /*String defaultMessage = bindingResult.getFieldError().getDefaultMessage();
            System.out.println("错误信息为："+defaultMessage);*/
                return HttpResult.failure(str.toString());
            }
            userService.register(user);
            return HttpResult.success();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return HttpResult.failure();
    }

    /*@PostMapping("/register")
    public ResponseResult register(@Valid @RequestBody User user){

            this.userService.register(user);
            return ResponseEntity.status(HttpStatus.CREATED).build()
    }*/
    /*@GetMapping("/query")
    public ResponseEntity<User> queryUser(@RequestParam("username")String username, @RequestParam("password")String password){
        User user = this.userService.queryUser(username, password);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }*/

    @GetMapping("/query")
    public User queryUser(@RequestParam("username")String username, @RequestParam("password")String password){
        User user = this.userService.queryUser(username, password);
        if (user == null) {
            return null;
        }
        return user;
    }

    @PostMapping("/findUser")
    public Page<User> findUser(@RequestParam("page") int page, @RequestParam("size") int size,@RequestBody CriteriaUser criteriaUser){
        return userService.findUser(page,size,criteriaUser);
    }

    /**
     * 查询权限列表
     * @param id
     * @return
     */
    @GetMapping("/authList")
    public List<Map<String,String>> authList(Long id){
       return this.userService.authList(id);
    }


    /**
     * 添加权限路径
     * @param urls
     * @return
     */
    @PostMapping("/addAuthUrl")
    public HttpResult addAuthUrl(@RequestBody Urls urls){
        try {
            urlsService.addAuthUrl(urls);
            return HttpResult.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HttpResult.failure();
    }

    /**
     * 添加角色
     * @param roles
     * @return
     */
    @PostMapping("/addRoles")
    @Transactional(rollbackFor = Exception.class)
    public HttpResult addRoles(@RequestBody Roles roles){
        try {
            //新增角色
            rolesService.addAuthUrl(roles);
            //根据角色对应权限路径id查询对应路径
            boolean is = addRedisAuth(roles.getId());
            if (is) {
             return HttpResult.success();
            }
            //如果redis存储失败则手动回滚事物
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return HttpResult.failure();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HttpResult.failure();
    }

    /**
     * 更新角色
     * @param roles
     * @return
     */
    @PostMapping("/updateRoles")
    @Transactional(rollbackFor = Exception.class)
    public HttpResult updateRoles(@RequestBody Roles roles){
        try {
            rolesService.updateRoles(roles);
            boolean is = addRedisAuth(roles.getId());
            if(is){
                return HttpResult.success();
            }
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return HttpResult.failure();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HttpResult.failure();
    }


    @GetMapping("/findAllRoles")
    public Page<Roles> findAllRoles(@RequestParam("page") int page, @RequestParam("size") int size){
       return rolesService.findAllRoles(page, size);
    }

    /**
     * 向redis存储数据
     * @param id
     * @return
     */
    public boolean addRedisAuth(Long id){
        try {
            List<Map<String, String>> authList = userService.authList(id);
            String authKey = key + id.toString();
            redisTemplate.boundHashOps(authKeyList).put(authKey,authList);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 修改管理员禁用启用状态
     * @param id
     * @param state
     * @return
     */
    @GetMapping("/update")
    public int update(Long id,Integer state){
        return userService.update(id, state);
    }

    /**
     * 删除管理员
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public int delete(Long id){
        return userService.delete(id);
    }

    /**
     * 重置管理员密码
     * @param id
     * @return
     */
    @GetMapping("/resetPassword")
    public int resetPassword(Long id){
        return userService.resetPassword(id);
    }

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    @GetMapping("/getUserById")
    public User getUserById(Long id) {
        return userService.getUserById(id);
    }


    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PostMapping("/updateUset")
    public int updateUset(@RequestBody User user) {
        return userService.updateUset(user);
    }


    /**
     * 用户密码修改
     * @param id
     * @param password
     * @param newpassword
     * @return
     */
    @GetMapping("/updatePassword")
    public int updatePassword(@RequestParam("id") Long id,
                              @RequestParam("password") String password,
                              @RequestParam("newpassword") String newpassword) {
        return userService.updatePassword(id, password, newpassword);
    }

    /**
     * 根据用户名查询账号是否存在
     * @param username
     * @return
     */
    @GetMapping("/getUserByName")
    public User getUserByName(@RequestParam("username") String username) {
        return userService.getUserByName(username);
    }

    /**
     * 查询所有角色(不分页)
     * @return
     * */
    @GetMapping("/findRoles")
    public List<Roles> findRoles(){
         return rolesService.findAllRoles();
    }

    /**
     * 根据名称查询角色信息
     * @return
     * */
    @GetMapping("/getRolesByName")
    public Roles getRolesByName(@RequestParam("name") String name){
        return rolesService.getRolesByName(name);
    }

    /**
     * 根据id查询角色信息
     * @return
             * */
    @GetMapping("/getRolesById")
    public Roles getRolesById(@RequestParam("id") Long id){
        return rolesService.getRolesById(id);
    }


}
