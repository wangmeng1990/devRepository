package com.inuol.vo.user;

import com.inuol.user.Uinterest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UinterestVo extends Uinterest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 等级名称 */
	private String ulevelName;
}
