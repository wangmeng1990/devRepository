package com.inuol.bigdata;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_bigdata_communication")
@Data
@ApiModel(description= "通讯录实体类")
@ToString
public class TBigdataCommunication implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id自增长")
    private Long id;

    @ApiModelProperty(value = "联系人姓名")
    @NotNull(message = "联系人姓名不能为空")
    private String communicateName;

    @ApiModelProperty(value = "手机号码")
    @NotNull(message = "手机号码不能为空")
    private String phone;

    @ApiModelProperty(value = "办公电话")
    private String officeMobile;

    @ApiModelProperty(value = "部门id")
    @NotNull(message = "部门不能为空")
    private String departmentId;

    @ApiModelProperty(value = "职位id(角色id)")
    @NotNull(message = "职位不能为空")
    private String roleId;

    @ApiModelProperty(value = "性别：1=男，2=女")
    private String sex;

    @ApiModelProperty(value = "生日")
    private String birthday;

    @ApiModelProperty(value = "籍贯")
    private String jiguan;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

}