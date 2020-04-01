package com.inuol.vo;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("弹窗model")
public class PopVo extends BaseVo{
	@NotBlank(message = "【code：弹窗标识】不能为空")
	@ApiModelProperty(value = "弹窗标识",required = true)
    private String code;
	@NotBlank(message ="【name：弹窗名称】不能为空" )
	@ApiModelProperty(value = "弹窗名称",required = true)
    private String name;
	@NotBlank(message ="【type：弹窗类型】不能为空" )
	@ApiModelProperty(value = "弹窗类型",required = true)
    private String type;
	@ApiModelProperty(value = "跳转链接",required = false)
    private String url;
	@ApiModelProperty(value = "图片",required = false)
    private String img;
	@ApiModelProperty(value = "显示日期开始",required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "JMT+8")
    private Date startDate;
	@ApiModelProperty(value = "显示日期结束",required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "JMT+8")
    private Date endDate;
}
