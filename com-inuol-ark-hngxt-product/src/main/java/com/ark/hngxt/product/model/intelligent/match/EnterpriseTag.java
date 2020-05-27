package com.ark.hngxt.product.model.intelligent.match;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.util.Date;


@Data
@ApiModel(value = "企业标签")
public class EnterpriseTag {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "企业标签查询ID")
    private String companyTagQueryId;

    @ApiModelProperty(value = "企业ID")
    private Long companyId;

    @ApiModelProperty(value = "企业社会信用编码")
    private String creditCode;

    @ApiModelProperty(value = "原始报文")
    private String inputData;

    @ApiModelProperty(value = "报告输出")
    private String outputData;

    @ApiModelProperty(value = "查询状态:0-已发送,1-成功,2-失败,3-失效")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    private Date createAt;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    private Date updateAt;

    @ApiModelProperty(value = "消息id")
    private String messageId;

    @ApiModelProperty(value = "法人年龄")
    private int age;

    private String[] type;


}
