package com.inuol.vo.scenic;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * @author Capejor
 * @date 2020-01-03 10:17
 */
@Data
@ApiModel("路线返回对象")
public class RouteResult {
    private Long id;

    private String routeName;

    private String description;

    private String state;

    private String url;

    private Date createTime;

    private Date updateTime;

    private Long creator;

    private String routeSet;

}
