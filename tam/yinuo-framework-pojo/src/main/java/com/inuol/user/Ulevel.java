package com.inuol.user;

import javax.persistence.Id;
import javax.persistence.Table;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.inuol.common.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 会员等级
 * 
 * @author weiss
 *
 */
@Data
@Table(name = "t_ulevel")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Ulevel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@Id
	private String id;

	/** 等级编号 */
	private String levelCode;

	/** 等级名称 */
	private String levelName;

	/** 成长值下限 */
	@TableField(strategy = FieldStrategy.IGNORED)
	private Integer bottomLimitation;

	/** 成长值上限 */
	private Integer topLimitation;

	/** 有效期/天 */
	private Integer validDays;

	/** 保级条件：成长值大于等于相应值 */
	private Integer keepCondition;

	/** 排序 */
	private Integer sort;

	/** 状态：1-启用、2-停用 */
	private Integer status;

}
