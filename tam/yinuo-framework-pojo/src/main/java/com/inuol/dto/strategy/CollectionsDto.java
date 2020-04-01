package com.inuol.dto.strategy;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 旅游攻略收藏
 */
@ApiModel(value="旅游攻略收藏接口实体接收" )
@Data
public class CollectionsDto {
    @ApiModelProperty(value="新增不需要传id后台自动生成")
    private Long id;
    @NotNull(message = "攻略id不能为空")
    @ApiModelProperty(value="攻略id",example="111",required = true)
    private Long tid;//攻略id
    @ApiModelProperty(value="用户id 后台自动获取",example="12312",required = false)
    private Long uid;//用户id
}
