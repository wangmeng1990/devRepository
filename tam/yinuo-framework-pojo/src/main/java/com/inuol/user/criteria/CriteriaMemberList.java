package com.inuol.user.criteria;

import java.util.Date;

import com.inuol.user.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 会员列表条件
 * 
 * @author weiss
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CriteriaMemberList extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("注册时间起始")
	private Date timeBegin;
	
	@ApiModelProperty("注册时间结束")
	private Date timeEnd;

}
