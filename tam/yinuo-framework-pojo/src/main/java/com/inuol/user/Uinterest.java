package com.inuol.user;

import javax.persistence.Id;
import javax.persistence.Table;

import com.inuol.common.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 会员权益
 * 
 * @author weiss
 *
 */
@Data
@Table(name = "t_uinterest")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Uinterest extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;

	/** 会员Id */
	private String ulevelId;

	/** 权益名称 */
	private String interestName;

	/** 赠送金星 */
	private Integer goldStar;

	/** 配置福利：福利Id */
	private String welfareId;

	/** 权益icon */
	private String iconUrl;

	/** 权益说明 */
	private String description;

	/** 状态：1-启用、2-停用 */
	private Integer status;

}
