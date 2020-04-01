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
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_bigdata_warning")
@Data
@ApiModel(description= "预警实体类")
@ToString
public class BigdataWarning implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id自增长")
    private Long id;

    @ApiModelProperty(value = "预警编号")
    private String warningNumber;

    @ApiModelProperty(value = "预警类别")
    private String warningSort;

    @ApiModelProperty(value = "预警等级")
    private String warningLevel;

    @ApiModelProperty(value = "上报人id")
    private String reportPerson;

    @ApiModelProperty(value = "上报人姓名")
    private String reportPersonName;

    @ApiModelProperty(value = "上报内容")
    private String reportContent;

    @ApiModelProperty(value = "上报位置")
    private String reportSite;

    @ApiModelProperty(value = "上报照片")
    private String reportPicture;

    @ApiModelProperty(value = "预警分类")
    private String reportType;

    @ApiModelProperty(value = "上报时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date reportTime;


}