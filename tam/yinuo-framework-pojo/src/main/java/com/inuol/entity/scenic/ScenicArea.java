package com.inuol.entity.scenic;

import java.util.Date;

public class ScenicArea {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_scenic_area.id
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_scenic_area.scenic_name
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    private String scenicName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_scenic_area.open_time
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    private String openTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_scenic_area.general_situation
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    private String generalSituation;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_scenic_area.cover
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    private String cover;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_scenic_area.voice
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    private String voice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_scenic_area.is_show
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    private String isShow;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_scenic_area.create_time
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_scenic_area.update_time
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_scenic_area.creator
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    private Long creator;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_scenic_area.id
     *
     * @return the value of t_scenic_area.id
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_scenic_area.id
     *
     * @param id the value for t_scenic_area.id
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_scenic_area.scenic_name
     *
     * @return the value of t_scenic_area.scenic_name
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public String getScenicName() {
        return scenicName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_scenic_area.scenic_name
     *
     * @param scenicName the value for t_scenic_area.scenic_name
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public void setScenicName(String scenicName) {
        this.scenicName = scenicName == null ? null : scenicName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_scenic_area.open_time
     *
     * @return the value of t_scenic_area.open_time
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public String getOpenTime() {
        return openTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_scenic_area.open_time
     *
     * @param openTime the value for t_scenic_area.open_time
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public void setOpenTime(String openTime) {
        this.openTime = openTime == null ? null : openTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_scenic_area.general_situation
     *
     * @return the value of t_scenic_area.general_situation
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public String getGeneralSituation() {
        return generalSituation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_scenic_area.general_situation
     *
     * @param generalSituation the value for t_scenic_area.general_situation
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public void setGeneralSituation(String generalSituation) {
        this.generalSituation = generalSituation == null ? null : generalSituation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_scenic_area.cover
     *
     * @return the value of t_scenic_area.cover
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public String getCover() {
        return cover;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_scenic_area.cover
     *
     * @param cover the value for t_scenic_area.cover
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_scenic_area.voice
     *
     * @return the value of t_scenic_area.voice
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public String getVoice() {
        return voice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_scenic_area.voice
     *
     * @param voice the value for t_scenic_area.voice
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public void setVoice(String voice) {
        this.voice = voice == null ? null : voice.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_scenic_area.is_show
     *
     * @return the value of t_scenic_area.is_show
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public String getIsShow() {
        return isShow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_scenic_area.is_show
     *
     * @param isShow the value for t_scenic_area.is_show
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public void setIsShow(String isShow) {
        this.isShow = isShow == null ? null : isShow.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_scenic_area.create_time
     *
     * @return the value of t_scenic_area.create_time
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_scenic_area.create_time
     *
     * @param createTime the value for t_scenic_area.create_time
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_scenic_area.update_time
     *
     * @return the value of t_scenic_area.update_time
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_scenic_area.update_time
     *
     * @param updateTime the value for t_scenic_area.update_time
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_scenic_area.creator
     *
     * @return the value of t_scenic_area.creator
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_scenic_area.creator
     *
     * @param creator the value for t_scenic_area.creator
     *
     * @mbg.generated Fri Jan 17 10:01:37 CST 2020
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }
}