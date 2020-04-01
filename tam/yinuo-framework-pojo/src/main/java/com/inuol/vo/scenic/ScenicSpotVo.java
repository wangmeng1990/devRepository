package com.inuol.vo.scenic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author Capejor
 * @date 2020-01-21 10:58
 */
@Data
@ApiModel("景点对象")
public class ScenicSpotVo {

    @ApiModelProperty(value = "id", required = false)
    private Long id;

    @ApiModelProperty(value = "景区id", required = false)
    private Long scenicAreaId;

    @ApiModelProperty(value = "景点名称", required = false)
    private String name;

    @ApiModelProperty(value = "排序", required = false)
    private String sort;

    @ApiModelProperty(value = "是否展示", required = false)
    private String isShow;


}
