package com.yinuo.scenic.service.impl;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinuo.common.common.HttpResult;
import com.inuol.entity.scenic.Route;
import com.inuol.entity.scenic.RouteCriteria;
import com.inuol.entity.scenic.RouteSet;
import com.inuol.vo.scenic.RouteResult;
import com.inuol.vo.scenic.RouteVo;
import com.yinuo.scenic.mapper.RouteMapper;
import com.yinuo.scenic.mapper.RouteSetMapper;
import com.yinuo.scenic.service.RouteService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Capejor
 * @date 2020-01-02 11:15
 */
@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteMapper routeMapper;

    @Autowired
    private RouteSetMapper routeSetMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult insertRoute(RouteVo vo) {
        Route route = new Route();
        BeanUtils.copyProperties(vo, route);
        route.setId(IdWorker.getId());
        route.setCreateTime(new Date());
        route.setCreator(1L);
        int resultNum;
        resultNum = routeMapper.insertSelective(route);
        if (resultNum == 0) {
            return HttpResult.retCol(-1);
        }
        for (int i = 0; i < vo.getRouteSetVos().size(); i++) {
            RouteSet routeSet = new RouteSet();
            BeanUtils.copyProperties(vo.getRouteSetVos().get(i), routeSet);
            routeSet.setId(IdWorker.getId());
            routeSet.setRouteId(vo.getId());
            routeSet.setCreateTime(new Date());
            routeSet.setCreator(1L);
            resultNum = routeSetMapper.insertSelective(routeSet);
            if (resultNum == 0) {
                return HttpResult.retCol(-1);
            }
        }
        return HttpResult.retCol(resultNum);
    }

    @Override
    @Transactional()
    public HttpResult updateRoute(RouteVo vo) {
        int resultNum;
        resultNum = routeSetMapper.deleteByRouteId(vo.getId());
        if (resultNum == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return HttpResult.retCol(-1);
        }

        Route route = new Route();
        route.setUpdateTime(new Date());
        resultNum = routeMapper.updateByPrimaryKeySelective(route);
        if (resultNum == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return HttpResult.retCol(-1);
        }
        for (int i = 0; i < vo.getRouteSetVos().size(); i++) {
            RouteSet routeSet = new RouteSet();
            BeanUtils.copyProperties(vo.getRouteSetVos().get(i), routeSet);
            routeSet.setId(IdWorker.getId());
            routeSet.setRouteId(vo.getId());
            routeSet.setUpdateTime(new Date());
            resultNum = routeSetMapper.insertSelective(routeSet);
            if (resultNum == 0) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return HttpResult.retCol(-1);
            }
        }
        return HttpResult.retCol(resultNum);
    }

    /* @Override
     public Result selectAllRoute(Integer pageNum, Integer pageSize) {
         PageHelper.startPage(pageNum,pageSize);
         RouteCriteria routeCriteria= new RouteCriteria();
         List<Route> list = routeMapper.selectByExample(routeCriteria);
         PageInfo<Route> pageInfo = new PageInfo<>();
         return Result.success().setPageAndList(pageInfo,list);
     }*/
    @Override
    public HttpResult selectAllRoute(Integer pageNum, Integer pageSize, String routeName) {
        PageHelper.startPage(pageNum, pageSize);
        RouteCriteria routeCriteria = new RouteCriteria();
        routeCriteria.createCriteria().andRouteNameLike("%" + routeName + "%");
        List<RouteResult> list = routeMapper.selectAll(routeName);
        PageInfo<RouteResult> pageInfo = new PageInfo<>(list);
        return HttpResult.success(pageInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult deleteOneById(Long id) {
        int resultNum;
        resultNum = routeMapper.deleteByPrimaryKey(id);
        if (resultNum == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return HttpResult.retCol(-1);
        }
        resultNum = routeSetMapper.deleteByRouteId(id);
        if (resultNum == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return HttpResult.retCol(-1);
        }
        return HttpResult.retCol(resultNum);
    }

    @Override
    public int judgeRepeatByRouteName(String routeName) {
        return routeMapper.judgeRepeatByRouteName(routeName);
    }

    @Override
    public int judgeRepeatByRouteNameExceptOwn(Long id, String routeName) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("routeName", routeName);
        return routeMapper.judgeRepeatByRouteNameExceptOwn(map);
    }
}
