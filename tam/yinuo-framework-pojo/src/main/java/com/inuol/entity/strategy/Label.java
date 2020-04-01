package com.inuol.entity.strategy;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel(value="旅游攻略标签实体类" )
@TableName(value="t_travel_label")
@Data
public class Label {
    @TableId(value="id",type=IdType.ID_WORKER)
    private Long id;
    //标签内容
    @NotNull
    @ApiModelProperty(value = "标签内容",example="示例-一次完美的旅行",required = true)
    @Length(max=10)
    private String title;
    @NotNull
    @ApiModelProperty(value = "标签排序",example="示例-1",required = true)
    private int orderbyNum;
    @NotNull
    @ApiModelProperty(value = "标签状态1启用 2停用",example="示例-1",required = true)
    private int status;
    // 创建人id
    @ApiModelProperty(value="创建人uid 后台自动赋值",example="示例-123432 ",required = false)
    private Long createUserId;
    // 创建时间
    @ApiModelProperty(value="创建时间",example="示例- 2018-12-10 10:10:12 ",required = false)
    private Date createTime;
    // 更新人id
    @ApiModelProperty(value="最后一次更新人uid 后台自动赋值",example="示例-123432 ",required = false)
    private Long updateUserId;
    // 更新时间
    @ApiModelProperty(value="最后一次更新时间",example="示例- 2020-12-10 10:10:12 ",required = false)
    private Date updateTime;
}
