package com.inuol.vo.backstageAuth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Capejor
 * @date 2020-01-09 14:35
 */
@Data
@ApiModel("组织对象")
public class OrganizationVo {

    @ApiModelProperty(value = "id",required = false)
    private Long id;

    @ApiModelProperty(value = "父级id",required = true)
    private Long parentId;

    @ApiModelProperty(value = "组织编码",required = true)
    private String orgCode;

    @ApiModelProperty(value = "备注",required = false)
    private String remarks;

    @ApiModelProperty(value = "组织名称",required = true)
    private String orgName;

    @ApiModelProperty(value = "创建时间",required = false)
    private Date createTime;

    @ApiModelProperty(value = "更新时间",required = false)
    private Date updateTime;

    @ApiModelProperty(value = "创建人",required = false)
    private Long creator;

}
