package com.yinuo.scenic.service.impl;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.inuol.entity.scenic.ScenicLabel;
import com.inuol.entity.scenic.ScenicLabelCriteria;
import com.inuol.entity.strategy.Label;
import com.inuol.vo.scenic.ScenicLabelVo;
import com.yinuo.common.common.HttpResult;
import com.yinuo.scenic.mapper.AreaLabelMapper;
import com.yinuo.scenic.mapper.ScenicLabelMapper;
import com.yinuo.scenic.service.ScenicLabelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Capejor
 * @date 2020-02-11 11:50
 */
@Service
public class ScenicLabelServiceImpl implements ScenicLabelService {

    @Autowired
    private ScenicLabelMapper scenicLabelMapper;

    @Autowired
    private AreaLabelMapper areaLabelMapper;

    @Override
    public HttpResult insertScenicLabel(ScenicLabelVo vo) {
        int resultCount = scenicLabelMapper.selectCountByLabelName(vo.getLabelName());
        if (resultCount > 0) {
            return HttpResult.failure("404", "标签名称不能重复！");
        }
        ScenicLabel scenicLabel = new ScenicLabel();
        BeanUtils.copyProperties(vo, scenicLabel);
        scenicLabel.setId(IdWorker.getId());
        scenicLabel.setCreateTime(new Date());
        scenicLabel.setCreator(1L);
        return HttpResult.success(scenicLabelMapper.insertSelective(scenicLabel));
    }

    @Override
    public HttpResult updateScenicLabel(ScenicLabelVo vo) {
        int resultCount = areaLabelMapper.selectCountByScenicAreaId(vo.getId());
        if (resultCount > 0) {
            return HttpResult.failure("400", "该标签已经绑定景区，不能删除！");
        }

        ScenicLabel scenicLabel = new ScenicLabel();
        BeanUtils.copyProperties(vo, scenicLabel);
        scenicLabel.setUpdateTime(new Date());
        return HttpResult.success(scenicLabelMapper.updateByPrimaryKeySelective(scenicLabel));
    }

    @Override
    public HttpResult selectAllScenicLabel(int pageNum, int pageSize, String labelName) {
        PageHelper.startPage(pageNum, pageSize);
        ScenicLabelCriteria scenicLabelCriteria = new ScenicLabelCriteria();
        scenicLabelCriteria.createCriteria().andLabelNameLike("%" + labelName + "%");
        List<ScenicLabel> list = scenicLabelMapper.selectByExample(scenicLabelCriteria);
        PageInfo<ScenicLabel> pageInfo = new PageInfo<>(list);
        return HttpResult.success(pageInfo);
    }

    @Override
    public HttpResult deleteOneById(long id) {
        ScenicLabel scenicLabel = new ScenicLabel();
        scenicLabel.setId(id);
        scenicLabel.setUpdateTime(new Date());
        scenicLabel.setState("1");
        return HttpResult.success(scenicLabelMapper.updateByPrimaryKeySelective(scenicLabel));
    }
}
