package com.yinuo.api.user;


import com.github.pagehelper.Page;
import com.inuol.user.Roles;
import com.inuol.user.User;
import com.inuol.user.criteria.CriteriaUser;
import com.yinuo.common.common.HttpResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@FeignClient(value = "user",fallback = UserApiFallback.class)
public interface UserApi {

    @GetMapping("query")
    public User queryUser(@RequestParam("username") String username, @RequestParam("password") String password);

    @PostMapping("register")
    public HttpResult register(@Valid @RequestBody User user);

    @GetMapping("authList")
    public List<Map<String,String>> authList(Long id);

    @PostMapping("findUser")
    public Page<User> findUser(@RequestParam("page") int page, @RequestParam("size") int size, @RequestBody CriteriaUser criteriaUser);

    @GetMapping("delete")
    public int delete(@RequestParam("id") Long id);

    @GetMapping("update")
    public int update(@RequestParam("id") Long id, @RequestParam("state") Integer state);

    @GetMapping("resetPassword")
    public int resetPassword(@RequestParam("id") Long id);

    @GetMapping("findAllRoles")
    public Page<Roles> findAllRoles(@RequestParam("page") int page, @RequestParam("size") int size);

    @GetMapping("getUserById")
    public User getUserById( @RequestParam("id")Long id);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PostMapping("updateUset")
    public int updateUset(@RequestBody User user);

    /**
     * 用户密码修改
     * @param id
     * @param password
     * @param newpassword
     * @return
     */
    @GetMapping("updatePassword")
    public int updatePassword(@RequestParam("id") Long id,
                              @RequestParam("password") String password,
                              @RequestParam("newpassword") String newpassword);


    /**
     * 根据用户名查询账号是否存在
     * @param username
     * @return
     */
    @GetMapping("getUserByName")
    public User getUserByName(@RequestParam("username") String username);

    /**
     * 查询所有角色(不分页)
     * @return
     * */
    @GetMapping("findRoles")
    public List<Roles> findRoles();

    /**
     * 添加角色
     * @param roles
     * @return
     */
    @PostMapping("/addRoles")
    public HttpResult addRoles(@RequestBody Roles roles);

    /**
     * 根据名称查询角色信息
     * @return
     * */
    @GetMapping("/getRolesByName")
    public Roles getRolesByName(@RequestParam("name") String name);

    /**
     * 根据id查询角色信息
     * @return
     * */
    @GetMapping("/getRolesById")
    public Roles getRolesById(@RequestParam("id") Long id);

}
