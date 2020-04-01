package com.inuol.bigdata;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "t_bigdata_message")
@ApiModel(description= "消息实体类")
public class TBigdataMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id自增长")
    private Long id;

    @ApiModelProperty(value = "预警类型")
    private String warningType;

    @ApiModelProperty(value = "预警级别")
    private String warningLevel;

    @ApiModelProperty(value = "预警消息内容")
    private String messageContent;

    @ApiModelProperty(value = "添加时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "是否已读")
    private Boolean isRead;

    @ApiModelProperty(value = "消息归属人id")
    private String belongsId;

}