package com.inuol.vo.scenic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Capejor
 * @date 2020-01-07 11:04
 */
@Data
@ApiModel("渠道对象")
public class ChannelVo {

    @ApiModelProperty(value = "id",required = false)
    private Long id;

    @ApiModelProperty(value = "渠道名称",required = false)
    private String name;

    @ApiModelProperty(value = "渠道代码",required = false)
    private String code;

    @ApiModelProperty(value = "开放时间",required = false)
    private String openTime;

    @ApiModelProperty(value = "渠道链接",required = false)
    private String url;

    @ApiModelProperty(value = "启用状态：1启用，0禁用",required = false)
    private String state;

//    @ApiModelProperty(value = "创建时间",required = false)
//    private Date createTime;
//
//    @ApiModelProperty(value = "更新时间",required = false)
//    private Date updateTime;
//
//    @ApiModelProperty(value = "创建人",required = false)
//    private Long creator;

}
