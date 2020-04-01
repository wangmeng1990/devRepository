package com.inuol.vo.scenic;

import java.util.List;

import com.inuol.entity.scenic.ScenicPhoto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("景区对象")
public class ScenicAreaVo {

    @ApiModelProperty(value = "id", required = false)
    private Long id;

    @ApiModelProperty(value = "景区名称", required = false)
    private String scenicName;

    @ApiModelProperty(value = "开放时间", required = false)
    private String openTime;

    @ApiModelProperty(value = "概况", required = false)
    private String generalSituation;

    @ApiModelProperty(value = "封面", required = false)
    private String cover;

    @ApiModelProperty(value = "语音包", required = false)
    private String voice;

    @ApiModelProperty(value = "是否展示", required = false)
    private String isShow;

//    private Date createTime;
//
//    private Date updateTime;
//
//    private Long creator;

    @ApiModelProperty(value = "是否展示", required = false)
    private List<ScenicPhoto> scenicPhotos;

}