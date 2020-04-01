package com.inuol.vo.scenic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Capejor
 * @date 2020-01-02 16:57
 */
@Data
@ApiModel("路线对象")
public class RouteVo {

    @ApiModelProperty(value = "id",required = false)
    private Long id;

    @ApiModelProperty(value = "路线名称",required = false)
    private String routeName;

    @ApiModelProperty(value = "描述",required = false)
    private String description;

    @ApiModelProperty(value = "启用状态：1启用，0禁用",required = false)
    private String state;

    @ApiModelProperty(value = "图像地址",required = false)
    private String url;

//    @ApiModelProperty(value = "创建时间",required = false)
//    private Date createTime;
//
//    @ApiModelProperty(value = "更新时间",required = false)
//    private Date updateTime;
//
//    @ApiModelProperty(value = "创建人",required = false)
//    private Long creator;

    @ApiModelProperty(value = "路线设置集合")
    private List<RouteSetVo> routeSetVos;


}
