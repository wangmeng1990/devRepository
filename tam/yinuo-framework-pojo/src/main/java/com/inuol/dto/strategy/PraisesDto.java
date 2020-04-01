package com.inuol.dto.strategy;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 攻略点赞
 */
@ApiModel(value="旅游攻略点赞接口实体接收" )
@Data
public class PraisesDto {
    private Long id;
    @NotNull(message = "攻略id不能为空")
    @ApiModelProperty(value="攻略id",example="111",required = true)
    private Long tid;//攻略id
    @ApiModelProperty(value="用户id 不需要传",example="12312",required = false)
    private Long uid;//用户id
    @NotNull(message = "状态不能为空")
    @ApiModelProperty(value="0取消赞 1有效赞",example="1",required = true)
    private int status;//0取消赞 1有效赞
    private Date updateTime;
}
