package com.yinuo.scenic.service.impl;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.inuol.entity.scenic.Vr;
import com.inuol.vo.scenic.VrVo;
import com.yinuo.common.common.HttpResult;
import com.yinuo.scenic.mapper.VrMapper;
import com.yinuo.scenic.service.VrService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Capejor
 * @date 2020-01-22 15:35
 */
@Service
public class VrServiceImpl implements VrService {

    @Autowired
    private VrMapper vrMapper;

    @Override
    public HttpResult insertVrDescription(VrVo vo) {
        Vr vr = new Vr();
        BeanUtils.copyProperties(vo,vr);
        int count = vrMapper.selectCountByScenicSpotId(vo.getScenicSpotId());
        if (count >0){
            vr.setUpdateTime(new Date());
            return HttpResult.success(vrMapper.updateByPrimaryKeySelective(vr));
        }else {
            vr.setId(IdWorker.getId());
            vr.setCreateTime(new Date());
            vr.setCreator(1L);
            return HttpResult.success(vrMapper.insertSelective(vr));
        }
    }

    @Override
    public HttpResult insertVrPhoto(VrVo vo) {
        Vr vr = new Vr();
        BeanUtils.copyProperties(vo,vr);
        int count = vrMapper.selectCountByScenicSpotId(vo.getScenicSpotId());
        if (count >0){
            vr.setUpdateTime(new Date());
            return HttpResult.success(vrMapper.updateByPrimaryKeySelective(vr));
        }else {
            vr.setId(IdWorker.getId());
            vr.setCreateTime(new Date());
            vr.setCreator(1L);
            return HttpResult.success(vrMapper.insertSelective(vr));
        }
    }
}
