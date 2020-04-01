package com.inuol.vo.scenic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Capejor
 * @date 2020-01-07 10:50
 */
@Data
@ApiModel("旅游帮助对象")
public class TourismHelpVo {

    @ApiModelProperty(value = "id",required = false)
    private Long id;

    @ApiModelProperty(value = "问题类型",required = false)
    private String questionType;

    @ApiModelProperty(value = "排序",required = false)
    private String sort;

    @ApiModelProperty(value = "启用状态：1启用，0禁用",required = false)
    private String state;

    @ApiModelProperty(value = "显示终端:pc，ios，android",required = false)
    private String terminal;

//    @ApiModelProperty(value = "创建时间",required = false)
//    private Date createTime;
//
//    @ApiModelProperty(value = "更新时间",required = false)
//    private Date updateTime;
//
//    @ApiModelProperty(value = "创建人",required = false)
//    private Long creator;


}
