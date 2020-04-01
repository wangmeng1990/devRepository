package com.inuol.user.criteria;

import com.inuol.user.User;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author ：jias
 * @date ：2020/1/8 11:58
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CriteriaUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String keyWord; // or条件查询关键字

	private String ulabelNames; // 标签名称

	private String ulevelName; // 会员等级名称
}
