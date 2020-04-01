package com.yinuo.scenic.service;

import com.yinuo.common.common.HttpResult;
import com.inuol.vo.scenic.RouteVo;

/**
 * @author Capejor
 * @date 2020-01-02 11:15
 */
public interface RouteService {

    HttpResult insertRoute(RouteVo vo);

    HttpResult updateRoute(RouteVo vo);

    HttpResult selectAllRoute(Integer pageNum, Integer pageSize, String routeName);

    HttpResult deleteOneById(Long id);

    int judgeRepeatByRouteName(String routeName);

    int judgeRepeatByRouteNameExceptOwn(Long id, String routeName);

}
