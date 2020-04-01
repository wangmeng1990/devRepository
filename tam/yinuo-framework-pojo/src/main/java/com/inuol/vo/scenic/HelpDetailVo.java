package com.inuol.vo.scenic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Capejor
 * @date 2020-01-07 10:08
 */
@Data
@ApiModel("帮助详情对象")
public class HelpDetailVo  {

    @ApiModelProperty(value = "id",required = false)
    private Long id;

    @ApiModelProperty(value = "旅游帮助id")
    private Long tourismHelpId;

    @ApiModelProperty(value = "问题")
    private String question;

    @ApiModelProperty(value = "答案")
    private String answer;

    @ApiModelProperty(value = "排序",required = false)
    private String sort;

    @ApiModelProperty(value = "启用状态：1启用，0禁用",required = false)
    private String status;

//    @ApiModelProperty(value = "创建时间",required = false)
//    private Date createTime;
//
//    @ApiModelProperty(value = "更新时间",required = false)
//    private Date updateTime;
//
//    @ApiModelProperty(value = "创建人",required = false)
//    private Long creator;
}
