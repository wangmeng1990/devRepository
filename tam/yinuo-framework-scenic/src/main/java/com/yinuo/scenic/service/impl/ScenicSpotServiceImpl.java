package com.yinuo.scenic.service.impl;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.inuol.entity.scenic.ScenicSpot;
import com.inuol.vo.scenic.ScenicSpotVo;
import com.yinuo.common.common.HttpResult;
import com.yinuo.scenic.mapper.ScenicSpotMapper;
import com.yinuo.scenic.service.ScenicSpotService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Capejor
 * @date 2020-01-21 10:55
 */
@Service
public class ScenicSpotServiceImpl implements ScenicSpotService {

    @Autowired
    private ScenicSpotMapper scenicSpotMapper;

    @Override
    public HttpResult insertScenicSpot(ScenicSpotVo vo) {
        ScenicSpot scenicSpot = new ScenicSpot();
        BeanUtils.copyProperties(vo, scenicSpot);
        scenicSpot.setId(IdWorker.getId());
        scenicSpot.setCreateTime(new Date());
        scenicSpot.setCreator(1L);
        return HttpResult.retCol(scenicSpotMapper.insertSelective(scenicSpot));
    }

    @Override
    public HttpResult updateScenicSpot(ScenicSpotVo vo) {
        ScenicSpot scenicSpot = new ScenicSpot();
        BeanUtils.copyProperties(vo, scenicSpot);
        scenicSpot.setUpdateTime(new Date());
        return HttpResult.retCol(scenicSpotMapper.updateByPrimaryKeySelective(scenicSpot));
    }

    @Override
    public HttpResult selectAllScenicSpot(Integer pageNum, Integer pageSize, Long scenicAreaId, String name) {
        PageHelper.startPage(pageNum, pageSize);
//        ScenicSpotCriteria scenicSpotCriteria = new ScenicSpotCriteria();
//        scenicSpotCriteria.createCriteria().andScenicAreaIdEqualTo(scenicAreaId).andNameLike("%"+name+"%");
//        scenicSpotCriteria.createCriteria().andNameLike("%"+name+"%");
//        List<ScenicSpot> list = scenicSpotMapper.selectByExample(scenicSpotCriteria);
        List<ScenicSpot> list;
        if (scenicAreaId != null && name == null) {
            list = scenicSpotMapper.selectByScenicAreaId(scenicAreaId);
        } else if (scenicAreaId == null && name != null) {
            list = scenicSpotMapper.selectByName(name);
        } else if (scenicAreaId != null && name != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("scenicAreaId", scenicAreaId);
            map.put("name", name);
            list = scenicSpotMapper.selectByScenicAreaIdAndName(map);
        } else {
            list = scenicSpotMapper.selectAll();
        }
        PageInfo<ScenicSpot> pageInfo = new PageInfo<>(list);
        return HttpResult.success(pageInfo);
    }

    @Override
    public HttpResult deleteScenicSpotById(Long id) {
        ScenicSpot scenicSpot = new ScenicSpot();
        scenicSpot.setId(id);
        scenicSpot.setIsShow("0");
        return HttpResult.retCol(scenicSpotMapper.updateByPrimaryKeySelective(scenicSpot));
    }
}
