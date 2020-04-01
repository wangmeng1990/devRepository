package com.yinuo.common.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("通用集合响应返回对象")
public class ListResult<T> {
    @ApiModelProperty("总数量")
    private long total;
    @ApiModelProperty("集合")
    private List<T> rows;
}
