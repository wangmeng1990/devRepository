package com.inuol.vo.strategy;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@ApiModel(value="旅游攻略详情接口返回" )
@Data
public class TravelVo {

	    private Long id;
		//标题
	    @ApiModelProperty(value="标题",example="标题")
		private String title;
		//内容
	    @ApiModelProperty(value="内容",example="测试内容")
		private String contents;
		//标签id
	    @ApiModelProperty(value="标签id",example="111111")
		private Long labelId;

		@ApiModelProperty(value="标签名称",example="示例-一次不错的体验")
		private String labelName;

		@ApiModelProperty(value="附件类型 1图片 2视频",example="示例-1 ")
		private Integer annexType;
		//首图地址
		@ApiModelProperty(value="附件类型 图片 首图地址 ",example="示例-xxx.jpg ")
		private String img;
		//附件地址

		@ApiModelProperty(value="附件地址-图片多个以,拼接 ",example="示例-xxx.jpg,sss.jpg / aaa.avg ")
		private String annexUrls;

		@ApiModelProperty(value="发布景点地址名称",example="示例-故宫 ")
		private String createAddressName;

		private Integer topStatus;
		@ApiModelProperty(value="点击总数",example="示例-100 ")
		private long clicks;
		@ApiModelProperty(value="点赞总数",example="示例-100 ")
		private long praises;
		@ApiModelProperty(value="评论总数",example="示例-100 ")
		private long comments;
		@ApiModelProperty(value="收藏总数",example="示例-100 ")
	    private long  collections;
		@ApiModelProperty(value="发布人uid",example="示例-123432 ")
		private Long createUserId;
		@ApiModelProperty(value="发布人昵称",example="示例-张三 ")
		private String userName;
		@ApiModelProperty(value="发布人图像地址",example="示例-xxx.jpg ")
		private String userImgUrl;
		@ApiModelProperty(value="发布人创建时间",example="示例-2019-01-02 10:10:10 ")
		private Date createTime;

		@ApiModelProperty(value="点赞人集合 数量由前端传 点赞时间倒序")
		private List<UserVo> praisesUsers;
}
