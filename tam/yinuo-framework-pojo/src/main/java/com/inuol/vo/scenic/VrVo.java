package com.inuol.vo.scenic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author Capejor
 * @date 2020-01-22 16:14
 */
@Data
@ApiModel("景点vr对象")
public class VrVo {

    @ApiModelProperty(value = "id",required = false)
    private Long id;

    @ApiModelProperty(value = "scenicSpotId",required = false)
    private Long scenicSpotId;

    @ApiModelProperty(value = "url",required = false)
    private String url;

    @ApiModelProperty(value = "description",required = false)
    private String description;

}
