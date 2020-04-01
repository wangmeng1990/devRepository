package com.inuol.entity.scenic;

import java.util.Date;

public class Route {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_route.id
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_route.route_name
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    private String routeName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_route.description
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_route.state
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    private String state;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_route.url
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    private String url;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_route.create_time
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_route.update_time
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_route.creator
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    private Long creator;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_route.id
     *
     * @return the value of t_route.id
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_route.id
     *
     * @param id the value for t_route.id
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_route.route_name
     *
     * @return the value of t_route.route_name
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public String getRouteName() {
        return routeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_route.route_name
     *
     * @param routeName the value for t_route.route_name
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public void setRouteName(String routeName) {
        this.routeName = routeName == null ? null : routeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_route.description
     *
     * @return the value of t_route.description
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_route.description
     *
     * @param description the value for t_route.description
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_route.state
     *
     * @return the value of t_route.state
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_route.state
     *
     * @param state the value for t_route.state
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_route.url
     *
     * @return the value of t_route.url
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_route.url
     *
     * @param url the value for t_route.url
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_route.create_time
     *
     * @return the value of t_route.create_time
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_route.create_time
     *
     * @param createTime the value for t_route.create_time
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_route.update_time
     *
     * @return the value of t_route.update_time
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_route.update_time
     *
     * @param updateTime the value for t_route.update_time
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_route.creator
     *
     * @return the value of t_route.creator
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_route.creator
     *
     * @param creator the value for t_route.creator
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }
}