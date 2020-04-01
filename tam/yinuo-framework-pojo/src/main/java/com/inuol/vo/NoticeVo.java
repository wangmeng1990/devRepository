package com.inuol.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("公告对象")
public class NoticeVo extends BaseVo{
    @ApiModelProperty(value = "标志",required = false)
    private String code;
    @ApiModelProperty(value = "主题",required = false)
    private String subJect;
    @ApiModelProperty(value = "类型",required = false)
    private String type;
    @ApiModelProperty(value = "公布方式",required = false)
    private String plushType;
    @ApiModelProperty(value = "公布时间",required = false)
    private String publishDate;
    @ApiModelProperty(value = "内容",required = false)
    private String context;
}
