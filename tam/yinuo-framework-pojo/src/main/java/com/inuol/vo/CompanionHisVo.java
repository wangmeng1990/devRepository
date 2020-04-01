package com.inuol.vo;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("常用人")
public class CompanionHisVo {
	@ApiModelProperty(value = "id,编辑的时候需要传",required = false)
	private Long id;
	@ApiModelProperty(value = "当前用户id",required = false)
    private Integer uid;
	@ApiModelProperty(value = "中文姓名:证件类型为身份证必传",required = false)
    private String name;
	@ApiModelProperty(value = "手机号",required = false)
    private String mobile;
	@ApiModelProperty(value = "英文姓:证件类型为护照必传",required = false)
    private String firstName;
	@ApiModelProperty(value = "英文名:证件类型为护照必传",required = false)
    private String secondName;
	@ApiModelProperty(value = "国籍:证件类型为护照必传",required = false)
    private String nationality;
	@NotBlank(message = "【证件类型】不能为空")
	@NotNull(message = "【证件类型】不能为空")
	@ApiModelProperty(value = "证件类型：1身份证2护照",required = true)
    private String idType;
	@NotBlank(message = "【证件号码】不能为空")
	@ApiModelProperty(value = "证件号码",required = true)
    private String idCard;
	@NotBlank(message = "【证件有效期】不能为空")
	@ApiModelProperty(value = "证件有效期",required = true)
    private String validity;
	@ApiModelProperty(value = "是否本人1是0否",required = false)
    private Integer beSelf;
}
