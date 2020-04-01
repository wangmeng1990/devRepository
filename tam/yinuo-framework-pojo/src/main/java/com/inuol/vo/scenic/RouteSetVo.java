package com.inuol.vo.scenic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Capejor
 * @date 2020-01-07 10:28
 */
@Data
@ApiModel("路线设置对象")
public class RouteSetVo {

    @ApiModelProperty(value = "id",required = false)
    private Long id;

    @ApiModelProperty(value = "路线id")
    private Integer routeId;

    @ApiModelProperty(value = "位置名称")
    private String siteName;

    @ApiModelProperty(value = "排序",required = false)
    private String sort;

//    @ApiModelProperty(value = "创建时间",required = false)
//    private Date createTime;
//
//    @ApiModelProperty(value = "更新时间",required = false)
//    private Date updateTime;
//
//    @ApiModelProperty(value = "创建人",required = false)
//    private Long creator;


}
