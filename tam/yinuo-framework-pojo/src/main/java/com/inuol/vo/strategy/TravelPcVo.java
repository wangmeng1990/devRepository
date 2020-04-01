package com.inuol.vo.strategy;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value="旅游攻略-pc" )
@Data
public class TravelPcVo {
    private Long id;
    //标题
    @ApiModelProperty(value="标题",example="标题")
    private String title;

    @ApiModelProperty(value="发布景点地址名称",example="示例-故宫 ")
    private String createAddressName;

    private Integer topStatus;
    @ApiModelProperty(value="1正常 2屏蔽 3内容预警",example="示例-1 ")
    private Integer status;

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
    @ApiModelProperty(value="发布人手机号码",example="示例-18175164590 ")
    private String userPhone;
    @ApiModelProperty(value="发布时间",example="示例-2019-01-02 10:10:10 ")
    private Date createTime;
}
