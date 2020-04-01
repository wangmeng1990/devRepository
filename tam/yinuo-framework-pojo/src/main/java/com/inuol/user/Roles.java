package com.inuol.user;

import lombok.Data;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@ToString
@Table(name = "roles")
public class Roles implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //自增id

    private String name; //角色名称

    private String code; //对应权限id

    private String appAuth; //app端权限

    private String remark; //角色描述


}