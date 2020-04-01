package com.inuol.vo.strategy;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="用户" )
@Data
public class UserVo {
    // 用户id
    private Long id;
    // 用户昵称
    @ApiModelProperty(value="用户昵称",example="张三")
    private String name;
    // 用户图像
    @ApiModelProperty(value="用户图像",example="xxxx/aa.jpg")
    private String userImgUrl;
}
