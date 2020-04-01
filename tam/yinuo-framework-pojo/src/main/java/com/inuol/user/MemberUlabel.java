package com.inuol.user;

import javax.persistence.Table;

import com.inuol.common.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Table(name = "t_member_ulabel")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberUlabel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	/** 会员id */
	private Long userId;
	
	/** 标签id */
	private String ulabelId;

}
