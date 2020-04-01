package com.inuol.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel
public class TouristAppointment {
	
    private Long id;
    @ApiModelProperty(value = "预约编号")
    private String serialNo;
    @ApiModelProperty(value = "预约人编号")
    private Long uid;
    @ApiModelProperty(value = "预约人")
    private String name;
    @ApiModelProperty(value = "预约人手机号")
    private String mobile;
    @ApiModelProperty(value = "景点编号")
    private Long scenicId;
    @ApiModelProperty(value = "景点名称")
    private String scenicName;
    @ApiModelProperty(value = "预约方式app，pc")
    private String type;
    @ApiModelProperty(value = "预约状态：0待游玩1已游玩2已取消")
    private Integer status;
    @ApiModelProperty(value = "游玩日期")
    private String playDate;
    @ApiModelProperty(value = "游玩时间段")
    private String timeQuantum;
    @ApiModelProperty(value = "同行人数")
    private Integer companion;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "JMT+8")
    @ApiModelProperty(value = "预约时间")
    private Date createDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "JMT+8")
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo == null ? null : serialNo.trim();
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Long getScenicId() {
        return scenicId;
    }

    public void setScenicId(Long scenicId) {
        this.scenicId = scenicId;
    }

    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName == null ? null : scenicName.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPlayDate() {
        return playDate;
    }

    public void setPlayDate(String playDate) {
        this.playDate = playDate == null ? null : playDate.trim();
    }

    public String getTimeQuantum() {
        return timeQuantum;
    }

    public void setTimeQuantum(String timeQuantum) {
        this.timeQuantum = timeQuantum == null ? null : timeQuantum.trim();
    }

    public Integer getCompanion() {
        return companion;
    }

    public void setCompanion(Integer companion) {
        this.companion = companion;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}