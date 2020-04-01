package com.yinuo.scenic.service.impl;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinuo.common.common.HttpResult;
import com.inuol.entity.scenic.TourismHelp;
import com.inuol.entity.scenic.TourismHelpCriteria;
import com.inuol.vo.scenic.TourismHelpVo;
import com.yinuo.scenic.mapper.TourismHelpMapper;
import com.yinuo.scenic.service.TourismHelpService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Capejor
 * @date 2019-12-30 16:52
 */
@Service
public class TourismHelpServiceImpl implements TourismHelpService {

    @Autowired
    private TourismHelpMapper tourismHelpMapper;


    @Override
    public HttpResult insertTourismHelp(TourismHelpVo vo) {
        TourismHelp tourismHelp = new TourismHelp();
        BeanUtils.copyProperties(vo,tourismHelp);
        tourismHelp.setId(IdWorker.getId());
        tourismHelp.setCreateTime(new Date());
        tourismHelp.setCreator(1L);
        return HttpResult.retCol(tourismHelpMapper.insertSelective(tourismHelp));
    }

    @Override
    public HttpResult updateTourismHelp(TourismHelpVo vo) {
        TourismHelp tourismHelp = new TourismHelp();
        BeanUtils.copyProperties(vo,tourismHelp);
        tourismHelp.setUpdateTime(new Date());
        return HttpResult.retCol(tourismHelpMapper.updateByPrimaryKeySelective(tourismHelp));
    }

    @Override
    public HttpResult selectAllTourismHelp(Integer pageNum, Integer pageSize,String questionType) {
        PageHelper.startPage(pageNum,pageSize);
        TourismHelpCriteria example = new TourismHelpCriteria();
        example.createCriteria().andQuestionTypeLike("%"+questionType+"%");
        //example.setOrderByClause(" sort desc ");
        List<TourismHelp> list = tourismHelpMapper.selectByExample(example);
        PageInfo<TourismHelp> pageInfo = new PageInfo<>(list);
        return HttpResult.success(pageInfo);
    }

    @Override
    public HttpResult deleteOneById(Long id) {
        TourismHelp tourismHelp = new TourismHelp();
        tourismHelp.setId(id);
        tourismHelp.setState("0");
        return HttpResult.retCol(tourismHelpMapper.updateByPrimaryKeySelective(tourismHelp));
    }

    @Override
    public HttpResult updateTourismHelpState(Long id, String state) {
        TourismHelp record = new TourismHelp();
        record.setId(id);
        record.setState(state);
        return HttpResult.retCol(tourismHelpMapper.updateByPrimaryKeySelective(record));
    }

}
