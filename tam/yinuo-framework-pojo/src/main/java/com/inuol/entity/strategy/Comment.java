package com.inuol.entity.strategy;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
public class Comment {

    private Long id;

    private Long tid;//攻略id

    private Long uid;//用户id

    private Long replyUid;

    private Long praises;//点赞总数

    private String title;
    //1正常 2删除 3屏蔽
    private int delFlag;

    private Date createTime;
    private Date updateTime;
    private Long updateUserId;

}
