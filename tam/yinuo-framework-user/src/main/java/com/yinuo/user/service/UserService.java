package com.yinuo.user.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.inuol.user.User;
import com.inuol.user.criteria.CriteriaUser;
import com.yinuo.user.mapper.UserMapper;
import com.yinuo.user.utils.CodecUtils;

import tk.mybatis.mapper.entity.Example;

@Service
@Transactional
public class UserService {
    private  final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserMapper userMapper;


   /* @Autowired
    private AmqpTemplate amqpTemplate;*/

    /*@Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY_PREFIX = "user:verify:";*/

    /**
     * 校验数据是否可用
     * @param data
     * @param type
     * @return
     */
    public Boolean checkUser(String data, Integer type) {
        User record = new User();
        if (type == 1) {
            record.setUsername(data);
        } else if (type == 2) {
            record.setPhone(data);
        } else {
            return null;
        }
        return this.userMapper.selectCount(record) == 0;
    }

    /*public void sendVerifyCode(String phone) {
        if (StringUtils.isBlank(phone)) {
            return ;
        }

        // 生成验证码
        String code = NumberUtils.generateCode(6);

        // 发送消息到rabbitMQ
        *//*Map<String, String> msg = new HashMap<>();
        msg.put("phone", phone);
        msg.put("code", code);
        this.amqpTemplate.convertAndSend("leyou.sms.exchange", "verifycode.sms", msg);*//*

        // 把验证码保存到redis中
        this.redisTemplate.opsForValue().set(KEY_PREFIX + phone, code, 5, TimeUnit.MINUTES);
    }*/

    public void register(User user) {
        /*// 查询redis中验证码
        String redisCode = this.redisTemplate.opsForValue().get(KEY_PREFIX + user.getPhone());
        // 1.校验验证码
        if (!StringUtils.equals(code, redisCode)) {
            return ;
        }*/
        // 2. 生成盐
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);

        if (user.getUserType() == 0){
            String password = "tam123456";
            // 3.加盐加密
            user.setPassword(CodecUtils.md5Hex(password, salt));
        } else {
            // 3.加盐加密
            user.setPassword(CodecUtils.md5Hex(user.getPassword(), salt));
        }
        // 4.新增用户
        //user.setId(null);
        user.setTime(new Date());
        //user.setId(new IdWorker().nextId());
        this.userMapper.insertSelective(user);
    }

    public User queryUser(String username, String password) {
        User record = new User();
        record.setUsername(username);
        User user = this.userMapper.selectOne(record);

        // 判断user是否为空
        if (user == null) {
            return null;
        }

        // 获取盐，对用户输入的密码加盐加密
        password = CodecUtils.md5Hex(password, user.getSalt());

        // 和数据库中的密码比较
        if (StringUtils.equals(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public Page<User> findUser(int page, int size, CriteriaUser criteriaUser) {
        PageHelper.startPage(page, size);
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(criteriaUser.getUserType().toString())) {
            criteria.andEqualTo("userType",criteriaUser.getUserType());
        }
        if (StringUtils.isNotEmpty(criteriaUser.getDepartment())){
            criteria.andEqualTo("department",criteriaUser.getDepartment());
        }
        if (StringUtils.isNotEmpty(criteriaUser.getKeyWord())){
            Example.Criteria OrCriteria = example.createCriteria();
            OrCriteria.orLike("username","%"+criteriaUser.getKeyWord()+"%")
                    .orLike("phone","%"+criteriaUser.getKeyWord()+"%")
                    .orLike("realityName","%"+criteriaUser.getKeyWord()+"%");
            example.and(OrCriteria);
        }
        List<User> users = userMapper.selectByExample(example);
        if (users.size()>0) {
            return (Page<User>)users;
        }
        return null;
    }


    /**
     * 更新管理用户状态
     * @param id
     * @param state
     * @return
     */
    public int update(Long id,Integer state){
        User user = new User();
        user.setId(id);
        user.setState(state);
       return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 删除管理员
     * @param id
     * @return
     */
    public int delete(Long id){
        return userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据角色id查询对应权限
     * @param id
     * @return
     */
    public List<Map<String,String>> authList(Long id) {
        return userMapper.authList(id);
    }


    /**
     * 重置密码
     * @param id
     * @return
     */
    public int resetPassword(Long id) {
        String password = "tam123456";
        User user = userMapper.selectByPrimaryKey(id);
        // 3.加盐加密
        if (user == null){
            return 0;
        }
        user.setPassword(CodecUtils.md5Hex(password, user.getSalt()));
        return userMapper.updateByPrimaryKey(user);
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    public User getUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }


    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public int updateUset(User user) {
        return userMapper.updateByPrimaryKeySelective(user);

    }

    /**
     * 用户密码修改
     * @param id
     * @param password
     * @param newpassword
     * @return
     */
    public int updatePassword(Long id, String password,String newpassword) {
        //根据id查询用户密码盐
        User user = userMapper.selectByPrimaryKey(id);
        // 获取盐，对用户输入的密码加盐加密
        password = CodecUtils.md5Hex(password, user.getSalt());
        if (StringUtils.equals(password, user.getPassword())){
            user.setPassword(CodecUtils.md5Hex(newpassword, user.getSalt()));
            return userMapper.updateByPrimaryKeySelective(user);
        }
          return 0;
    }

    /**
     * 根据用户名查询该用户是否存在
     * @param username
     * @return
     */
    public User getUserByName(String username){
        User record = new User();
        record.setUsername(username);
        User user = this.userMapper.selectOne(record);
        if (user == null){
            return null;
        }
        return user;
    }


}
