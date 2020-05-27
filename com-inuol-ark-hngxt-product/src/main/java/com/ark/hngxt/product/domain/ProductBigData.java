package com.ark.hngxt.product.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("与大数据交互记录")
@TableName(value="product_big_data")
public class ProductBigData {

	@TableId(value="id",type=IdType.ID_WORKER)
	private Long id;
	
	@ApiModelProperty(value = "交互类型:tagMatch|产品打标签", required = false)
	private String type;
	
	@ApiModelProperty(value = "请求参数", required = false)
	private String requestBody;
	
	@ApiModelProperty(value = "消息id", required = false)
	private String msgId;
	
	@ApiModelProperty(value = "消息内容", required = false)
	private String msg;
	
	@ApiModelProperty(value = "状态", required = true)
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
