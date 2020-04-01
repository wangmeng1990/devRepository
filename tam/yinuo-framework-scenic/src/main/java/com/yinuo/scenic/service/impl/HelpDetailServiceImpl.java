package com.yinuo.scenic.service.impl;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinuo.common.common.HttpResult;
import com.inuol.entity.scenic.HelpDetail;
import com.inuol.entity.scenic.HelpDetailCriteria;
import com.inuol.vo.scenic.HelpDetailVo;
import com.yinuo.scenic.mapper.HelpDetailMapper;
import com.yinuo.scenic.service.HelpDetailService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Capejor
 * @date 2019-12-31 13:44
 */
@Service
public class HelpDetailServiceImpl implements HelpDetailService {

    @Autowired
    private HelpDetailMapper helpDetailMapper;

    @Override
    public HttpResult insertHelpDetail(HelpDetailVo vo) {
        HelpDetail helpDetail = new HelpDetail();
        BeanUtils.copyProperties(vo, helpDetail);
        helpDetail.setId(IdWorker.getId());
        helpDetail.setCreateTime(new Date());
        helpDetail.setCreator(1L);
        return HttpResult.retCol(helpDetailMapper.insertSelective(helpDetail));
    }

    @Override
    public HttpResult updateHelpDetail(HelpDetailVo vo) {
        HelpDetail helpDetail = new HelpDetail();
        BeanUtils.copyProperties(vo, helpDetail);
        helpDetail.setUpdateTime(new Date());
        return HttpResult.retCol(helpDetailMapper.updateByPrimaryKeySelective(helpDetail));
    }

    @Override
    public HttpResult selectAllHelpDetail(Integer pageNum, Integer pageSize,String question) {
        PageHelper.startPage(pageNum,pageSize);
        HelpDetailCriteria helpDetailCriteria = new HelpDetailCriteria();
        helpDetailCriteria.createCriteria().andQuestionLike("%"+question+"%");
        List<HelpDetail> list = helpDetailMapper.selectByExample(helpDetailCriteria);
        PageInfo<HelpDetail> pageInfo = new PageInfo<>(list);
        return HttpResult.success(pageInfo);
    }

    @Override
    public HttpResult deleteOneById(Long id) {
        HelpDetail helpDetail = new HelpDetail();
        helpDetail.setId(id);
        helpDetail.setStatus("0");
        return HttpResult.retCol(helpDetailMapper.updateByPrimaryKeySelective(helpDetail));
    }

    @Override
    public HttpResult updateHelpDetailState(Long id, String state) {
        HelpDetail helpDetail = new HelpDetail();
        helpDetail.setId(id);
        helpDetail.setStatus(state);
         return HttpResult.retCol(helpDetailMapper.updateByPrimaryKeySelective(helpDetail));
    }
}
