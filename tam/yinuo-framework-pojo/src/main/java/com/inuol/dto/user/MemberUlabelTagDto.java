package com.inuol.dto.user;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户打标签参数")
public class MemberUlabelTagDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("用户Id")
	private Long userId;

	@ApiModelProperty("标签Id列表")
	private List<String> ulabelIds;
}
