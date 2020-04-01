package com.inuol.common;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(hidden = true)
	private Date createTime;
	@ApiModelProperty(hidden = true)
	private Date updateTime;
	@ApiModelProperty(hidden = true)
	private String createUser;
	@ApiModelProperty(hidden = true)
	private String updateUser;
	@ApiModelProperty(hidden = true)
	private Integer isValid;

}
