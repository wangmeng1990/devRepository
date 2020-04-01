package com.inuol.vo;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("消息对象")
public class MessageVo extends BaseVo{
	    @ApiModelProperty(value = "标志",required = false)
	    private String code;
	    @ApiModelProperty(value = "主题",required = false)
	    private String subJect;
	    @ApiModelProperty(value = "消息类型",required = false)
	    private String type;
	    @ApiModelProperty(value = "推送方式",required = false)
	    private String plushType;
	    @ApiModelProperty(value = "推送人群",required = false)
	    private String plushObejct;
	    @ApiModelProperty(value = "标签内容",required = false)
	    private String tag;
	    @ApiModelProperty(value = "内容",required = false)
	    private String context;
}
