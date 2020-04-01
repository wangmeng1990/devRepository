package com.inuol.vo.scenic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Capejor
 * @date 2020-02-11 13:48
 */
@Data
@ApiModel("景区标签")
public class ScenicLabelVo {

    @ApiModelProperty(value = "id",required = false)
    private Long id;

    @ApiModelProperty(value = "labelName",required = false)
    private String labelName;

    @ApiModelProperty(value = "state",required = false)
    private String state;
}
