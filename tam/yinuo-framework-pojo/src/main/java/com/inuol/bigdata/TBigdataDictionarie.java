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
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_bigdata_dictionarie")
@Data
@ApiModel(description= "字典实体类")
public class TBigdataDictionarie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id自增长")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String dictionaryName;

    @ApiModelProperty(value = "上级id")
    private Long parentId;

    @ApiModelProperty(value = "排序id")
    private Integer sortId;

    @ApiModelProperty(value = "是否启用")
    private Boolean isvalid;

    @ApiModelProperty(value = "描述")
    private String remark;

    @ApiModelProperty(value = "添加时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "对应的预警等级")
    private String warningLevel;

}