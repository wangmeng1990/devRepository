package com.inuol.dto.strategy;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 攻略评论
 */
@ApiModel(value="旅游攻略评论接口实体接收" )
@Data
public class CommentDto {
    @ApiModelProperty(value="新增不需要传id后台自动生成 删除需要传")
    private Long id;
    @NotNull(message = "攻略id不能为空")
    @ApiModelProperty(value="攻略id 删除需要传",example="111",required = true)
    private Long tid;//攻略id
    @ApiModelProperty(value="用户id 后台自动赋值",example="12312")
    private Long uid;//用户id
    @ApiModelProperty(value="回复用户id",example="12312")
    private Long replyUid;
    @NotNull(message = "评论内容不能为空")
    @ApiModelProperty(value="评论内容",example="哈哈，说的有道理")
    private String title;//评论内容
    //1正常 2删除 3屏蔽
    private int delFlag;
}
