package com.inuol.user;

import javax.persistence.Table;

import com.inuol.common.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Table(name = "t_ulabel")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Ulabel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	/** 标签名字 */
	private String name;
}
