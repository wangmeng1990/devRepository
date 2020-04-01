package com.inuol.entity.backstageAuth;

import java.util.Date;

public class Organization {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_organization.id
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_organization.state
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    private String state;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_organization.parent_id
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    private Long parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_organization.org_code
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    private String orgCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_organization.remarks
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    private String remarks;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_organization.org_name
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    private String orgName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_organization.create_time
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_organization.update_time
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_organization.creator
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    private Long creator;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_organization.id
     *
     * @return the value of t_organization.id
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_organization.id
     *
     * @param id the value for t_organization.id
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_organization.state
     *
     * @return the value of t_organization.state
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_organization.state
     *
     * @param state the value for t_organization.state
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_organization.parent_id
     *
     * @return the value of t_organization.parent_id
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_organization.parent_id
     *
     * @param parentId the value for t_organization.parent_id
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_organization.org_code
     *
     * @return the value of t_organization.org_code
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_organization.org_code
     *
     * @param orgCode the value for t_organization.org_code
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_organization.remarks
     *
     * @return the value of t_organization.remarks
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_organization.remarks
     *
     * @param remarks the value for t_organization.remarks
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_organization.org_name
     *
     * @return the value of t_organization.org_name
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_organization.org_name
     *
     * @param orgName the value for t_organization.org_name
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_organization.create_time
     *
     * @return the value of t_organization.create_time
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_organization.create_time
     *
     * @param createTime the value for t_organization.create_time
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_organization.update_time
     *
     * @return the value of t_organization.update_time
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_organization.update_time
     *
     * @param updateTime the value for t_organization.update_time
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_organization.creator
     *
     * @return the value of t_organization.creator
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_organization.creator
     *
     * @param creator the value for t_organization.creator
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }
}