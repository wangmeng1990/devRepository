package com.inuol.vo.strategy;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel(value="旅游攻略评论列表实体类" )
@Data
public class TravelCommentVo {
    @ApiModelProperty(value="旅游攻略评论主键",required = true)
    private Long id;
    @ApiModelProperty(value="旅游攻略id ",example="111",required = true)
    private Long tid;//攻略id
    @ApiModelProperty(value="评论用户id",example="12312",required = true)
    private Long uid;//用户id
    @ApiModelProperty(value="旅游攻略创建用户id",example="1231211")
    private Long createUid;//旅游攻略创建用户id

    @ApiModelProperty(value="评论用户昵称",example="张先生",required = true)
    private String userName;//用户id
    @ApiModelProperty(value="评论用户图像地址",example="xxx.jpg",required = true)
    private String userImgUrl;//用户id

    @ApiModelProperty(value="回复用户id",example="12312")
    private Long replyUid;
    @ApiModelProperty(value="回复用户昵称",example="李女士")
    private String replyUserName;//用户id
    @ApiModelProperty(value="回复用户图像地址",example="asdas.jpg")
    private String replyUserImgUrl;//用户id

    @ApiModelProperty(value="评论内容",example="哈哈，说的有道理",required = true)
    private String title;//评论内容
    @ApiModelProperty(value="评论时间",example="2019-12-10 01:01:56",required = true)
    private Date createTime;
    @ApiModelProperty(value="点赞总数",example="1200",required = true)
    private long praises;
    @ApiModelProperty(value="是否是回复消息 ",example="1",required = true)
    // 1 是回复消息 2不是回复消息
    private int replyStatus;
    @ApiModelProperty(value="是否是发布者 ",example="1",required = true)
    // 是否是发布者 1是 2否
    private int authStatus;
    @ApiModelProperty(value="格式化发布时间距离当前时间 ",example="3天前",required = true)
    // 格式化发布时间距离当前时间
    private String dateStr;
}
