package com.inuol.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "cust_customer_hy")
@Data
public class CustCustomerHy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String customerName;

    private String customerSource;

    private String customerSite;

    private String customerLevel;

    private String customerPhase;

    private String linkName;

    private String linkPhone;

    private String officeTitle;

    private String officePhone;

    @Column(name = "industry_1_level")
    private String industry1Level;

    @Column(name = "industry_2_level")
    private String industry2Level;

    private String email;

    private String qq;

    private String fax;

    private String remark;

    private String province;

    private String city;

    private String area;

    private String address;

    private String bankAccount;

    private String bank;

    private String bankAddress;

    private String taxId;

    private String companyId;

    private String beforeBelongsTo;

    private String belongsTo;

    private String creator;

    private Date createTime;

    private Date updateTime;

    private Integer maintainUserId;

    private String returnTaskCode;

    private Date assignDate;

    private Integer isBlacklist;

    private String reserve1;

    private String reserve2;

    private String reserve3;

}