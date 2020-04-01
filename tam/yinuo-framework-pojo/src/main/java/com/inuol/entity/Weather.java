package com.inuol.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("天气信息")
public class Weather {
	private Long id;
	@ApiModelProperty(value = "风向：西北风")
	private String windDir;
	@ApiModelProperty(value = "风力：3-4级")
	private String windSc;
	@ApiModelProperty(value = "晴雨：晴")
	private String condTxt;
	@ApiModelProperty(value = "温度：23℃")
	private String tmp;
	@ApiModelProperty(value = "空气质量：轻度污染")
	private String qlty;
	@ApiModelProperty(value = "查询时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "JMT+8")
	private Date queryDate;
	@ApiModelProperty(value = "天气日期")
	private String dayTime;
	@ApiModelProperty(value = "天气区域：天安门")
	private String location;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "JMT+8")
	private Date createDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "JMT+8")
	private Date updateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWindDir() {
		return windDir;
	}

	public void setWindDir(String windDir) {
		this.windDir = windDir == null ? null : windDir.trim();
	}

	public String getWindSc() {
		return windSc;
	}

	public void setWindSc(String windSc) {
		this.windSc = windSc == null ? null : windSc.trim();
	}

	public String getCondTxt() {
		return condTxt;
	}

	public void setCondTxt(String condTxt) {
		this.condTxt = condTxt == null ? null : condTxt.trim();
	}

	public String getTmp() {
		return tmp;
	}

	public void setTmp(String tmp) {
		this.tmp = tmp == null ? null : tmp.trim();
	}

	public String getQlty() {
		return qlty;
	}

	public void setQlty(String qlty) {
		this.qlty = qlty == null ? null : qlty.trim();
	}

	public Date getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(Date queryDate) {
		this.queryDate = queryDate;
	}

	public String getDayTime() {
		return dayTime;
	}

	public void setDayTime(String dayTime) {
		this.dayTime = dayTime == null ? null : dayTime.trim();
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location == null ? null : location.trim();
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