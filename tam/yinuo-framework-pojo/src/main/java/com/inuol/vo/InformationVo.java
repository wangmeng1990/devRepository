package com.inuol.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("资讯信息")
public class InformationVo extends BaseVo{
    @ApiModelProperty(value = "主题",required = false)
    private String subJect;
    @ApiModelProperty(value = "消息类型",required = false)
    private String type;
    @ApiModelProperty(value = "跳转链接",required = false)
    private String url;
    @ApiModelProperty(value = "图片",required = false)
    private String img;
    @ApiModelProperty(value = "排序",required = false)
    private Integer sort;
    @ApiModelProperty(value = "来源",required = false)
    private String source;
    @ApiModelProperty(value = "点击量",required = false)
    private Integer hits;
    @ApiModelProperty(value = "置首1是0否",required = false)
    private Integer placeHead;
    @ApiModelProperty(value = "内容",required = false)
    private String context;
}
