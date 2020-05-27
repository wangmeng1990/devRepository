package com.ark.hngxt.product.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("标签产品匹配")
@TableName(value="match_product")
public class MatchProduct {

	@TableId(value="id",type=IdType.ID_WORKER)
	private Long id;
	
	@ApiModelProperty(value = "产品id", required = false)
	private Long productId;
	
	@ApiModelProperty(value = "产品类型：1特色产品2金融产品", required = false)
	private String productType;
	
	@ApiModelProperty(value = "产品标签", required = false)
	private String tags;
	
	@ApiModelProperty(value = "状态1生效0失效", required = true)
	private Integer status;
	
	@ApiModelProperty(value = "创建人", required = false)
	private String createBy;

	@ApiModelProperty(value = "创建日期", required = false)
	private Date createDate;

	@ApiModelProperty(value = "修改人", required = false)
	private String updateBy;

	@ApiModelProperty(value = "更新时间", required = false)
	private Date updateDate;


}
