package com.inuol.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("文创")
public class CulturalCreationVo extends BaseVo{
    @ApiModelProperty(value = "背景图名称",required = false)
    private String backGround;
    @ApiModelProperty(value = "背景图类型",required = false)
    private String backGroundType;
    @ApiModelProperty(value = "图片",required = false)
    private String img;
}
