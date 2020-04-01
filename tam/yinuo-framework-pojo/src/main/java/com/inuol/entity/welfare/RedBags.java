package com.inuol.entity.welfare;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel(value="红包福利实体类" )
@TableName(value="t_welfare_red_bags")
@Data
public class RedBags {
    @TableId(value="id",type=IdType.ID_WORKER)
    private Long id;

    @NotNull(message = "福利编号不能为空")
    @ApiModelProperty(value = "福利编号",example="HB001",required = true)
    private String number;

    @NotNull(message = "福利名称不能为空")
    @ApiModelProperty(value = "福利名称",example="10元红包",required = true)
    @Length(max=20)
    private String name;

    @NotNull(message = "发放总数量不能为空")
    @ApiModelProperty(value = "发放总数量 -1 代表无限制",example="1000",required = true)
    private Integer counts;

    @NotNull(message = "限领次数不能为空")
    @ApiModelProperty(value = "单人限领次数",example="1",required = true)
    private Integer limitCounts;

    @NotNull(message = "发放期限类型不能为空")
    @ApiModelProperty(value = "发放期限 0无期限 1固定期限",example="1",required = true)
    private Integer grantTermType;

    @ApiModelProperty(value = "发放期限-固定期限开始时间",example="2020-02-11 00:00:01")
    private Date grantTermStart;

    @ApiModelProperty(value = "发放期限-固定期限结束时间",example="2020-02-12 00:00:01")
    private Date grantTermEnd;

    @NotNull(message = "面额不能为空")
    @ApiModelProperty(value = "面额",example="110")
    private Double money;

    @NotNull(message = "使用期限类型不能为空")
    @ApiModelProperty(value = "使用期限 0领取后 1固定期限",example="1",required = true)
    private Integer useTermType;

    @ApiModelProperty(value = "使用期限-固定期限开始时间",example="2020-02-15 00:00:01")
    private Date useTermStart;

    @ApiModelProperty(value = "使用期限-固定期限结束时间",example="2020-02-17 00:00:01")
    private Date useTermEnd;

    @ApiModelProperty(value = "使用期限-领取后xx天",example="3")
    private Integer useTermDay;

    @ApiModelProperty(value = "使用说明",example="按照说明书使用")
    private String useExplain;

    @ApiModelProperty(value = "状态(1 待提交 2待审核3审核未通过4审核已通过5已上线6已暂停7已下线)",example="1")
    private Integer status;

    @ApiModelProperty(value = "审核人id",example="1254121")
    private Long checkUserId;

    @ApiModelProperty(value = "审核时间",example="2020-02-17 10:20:01")
    private Date checkTime;

    @ApiModelProperty(value = "上线/投放时间",example="2020-02-18 10:20:01")
    private Date putTime;

    @ApiModelProperty(value = "领取总数",example="101")
    private Integer receives;

    @ApiModelProperty(value = "使用总数",example="21")
    private Integer uses;

    @ApiModelProperty(value="创建人uid",example="123432")
    private Long createUserId;

    // 创建时间
    @ApiModelProperty(value="创建时间",example="2020-02-18 10:20:01")
    private Date createTime;
    // 更新人id
    private Long updateUserId;
    // 更新时间
    private Date updateTime;

}
