package com.inuol.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Table(name = "user")
@Data
public class User implements Serializable {
	
	private static final long serialVersionUID=1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 4, max = 30, message = "用户名必须在4-30位之间")
    private String username;// 用户名

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date time;

    private String realityName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastLoginTime;

    @Length(min = 4, max = 30, message = "密码必须在4-30位之间")
    /*@JsonIgnore // 对象序列化为json字符串时，忽略该属性*/
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;// 密码

    private Byte status; //角色状态

    private String company; //上级部门

    private Integer roleId; //角色id

    private String department; //部门

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String salt; //盐

    @Pattern(regexp = "^1[356789]\\d{9}$", message = "手机号不合法")
    private String phone;

    private String idType; //证件类型

    private String idCard; //证件号码

    private String sex; //性别

    private String registChannel; //注册渠道

    private Integer beAuth; //实名认证1是0否

    private Integer state; //0停用1启用

    private String logo; //头像

    private String city; //居住城市

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date birthDay; //生日

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateDate; //更新时间

    private Integer  userType; //用户类型0管理用户1游客用户

    private String qq; //QQ号

    private String weChat; //微信号

	private String email; // 邮箱

	private String ulevelId; // 会员等级id

	private Integer growthValue; // 会员成长值

}