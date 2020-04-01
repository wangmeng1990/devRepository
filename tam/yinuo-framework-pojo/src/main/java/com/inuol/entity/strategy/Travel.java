package com.inuol.entity.strategy;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 
 *
 * Title: Travel
 *
 * Description: 旅游攻略
 *
 * @author tanf
 *
 * @date 2020年1月8日
 *
 */
@ApiModel(value="旅游攻略实体类" )
@TableName(value="t_travel")
@Data
public class Travel {
	@TableId(value="id",type=IdType.ID_WORKER)
	private Long id;
	//标题
	@NotNull(message = "标题不能为空")
    @ApiModelProperty(value = "标题",example="示例-标题内容",required = true)
	@Length(max=10)
	private String title;
	//内容
	@NotNull(message = "内容不能为空")
	@Length(max=1000,message = "标题内容不能超过1000个字符")
    @ApiModelProperty(value="内容",example="示例-测试内容",required = true)
	private String contents;
	//标签id
	@NotNull(message = "标签id不能为空")
    @ApiModelProperty(value="标签id",example="示例-123321",required = true)
	private Long labelId;

	@TableField(exist=false)
	@ApiModelProperty(value="标签名称",example="示例-一次不错的体验",required = false)
	private String labelName;
	//附件类型
	@NotNull(message = "附件类型不能为空")
    @ApiModelProperty(value="附件类型 1图片 2视频",example="示例-1 ",required = true)
	private Integer annexType;
	//首图地址
	@ApiModelProperty(value="附件类型 图片 首图地址 ",example="示例-xxx.jpg ",required = true)
	private String img;
	//附件地址
	@NotNull(message = "附件地址不能为空")
	@ApiModelProperty(value="附件地址-图片多个以,拼接 ",example="示例-xxx.jpg,sss.jpg / aaa.avg ",required = true)
	private String annexUrls;
	//发布景点地址名称
	@NotNull(message = "发布景点地址名称不能为空")
    @ApiModelProperty(value="发布景点地址名称",example="示例-故宫 ",required = true)
	private String createAddressName;
	//状态 1正常 2屏蔽 3敏感
	private Integer status;
	//内容检测结果
	//图片检测结果
	//视频检测结果
	
	//置顶状态 1 未置顶 2已置顶
	private Integer topStatus;
	//置顶时间
	private Date topTime;
	// 点击总数
	@TableField(exist=false)
	private long clicks;
	// 点赞总数
	@TableField(exist=false)
	private long praises;
	// 评论总数
	@TableField(exist=false)
	private long comments;
	// 收藏总数
	@TableField(exist=false)
	private long collections;
	// 创建人id
    @ApiModelProperty(value="发布人uid",example="示例-123432 ",required = false)
	private Long createUserId;
	@TableField(exist=false)
	@ApiModelProperty(value="发布人昵称",example="示例-张三 ",required = false)
	private String userName;
	@TableField(exist=false)
	@ApiModelProperty(value="发布人图像地址",example="示例-xxx.jpg ",required = false)
	private String userImgUrl;
	// 创建时间
	private Date createTime;
	// 更新人id
	private Long updateUserId;
	// 更新时间 
	private Date updateTime;

	@TableField(exist=false)
	@ApiModelProperty(value="攻略收藏id",example="示例-123456",required = false)
	private long cid;
	
}
