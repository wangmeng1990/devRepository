package com.inuol.bigdata.ext;

import com.inuol.bigdata.BigdataWarning;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@Data
@ApiModel(description= "预警查询条件实体类")
public class BigdataWarningNode extends BigdataWarning {

    @ApiModelProperty(value = "or条件查询关键字")
    private String keyWord;

    @ApiModelProperty(value = "上报时间开始")
    private String timeBegin;

    @ApiModelProperty(value = "上报时间结束")
    private String timeEnd;


}