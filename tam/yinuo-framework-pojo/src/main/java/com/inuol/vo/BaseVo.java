package com.inuol.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class BaseVo {
	    @ApiModelProperty(value = "id修改时必传",required = false)
	    private Long id;
	    @ApiModelProperty(value = "1启用0未启用",required = false)
	    private Integer state;
		@ApiModelProperty(value = "显示终端:pc，app",required = false)
	    private String deviceType;
}
