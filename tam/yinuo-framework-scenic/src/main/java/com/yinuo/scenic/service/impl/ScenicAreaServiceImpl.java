package com.yinuo.scenic.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinuo.common.common.HttpResult;
import com.inuol.entity.scenic.ScenicArea;
import com.inuol.entity.scenic.ScenicAreaCriteria;
import com.inuol.vo.scenic.ScenicAreaVo;
import com.yinuo.scenic.mapper.ScenicAreaMapper;
import com.yinuo.scenic.mapper.ScenicPhotoMapper;
import com.yinuo.scenic.service.ScenicAreaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

/**
 * @author Capejor
 * @date 2019-12-27 11:34
 */
@Service
public class ScenicAreaServiceImpl implements ScenicAreaService {

    @Autowired
    private ScenicAreaMapper scenicAreaMapper;

    @Autowired
    private ScenicPhotoMapper scenicPhotoMapper;


    @Override
    public HttpResult updateScenicArea(ScenicAreaVo scenicAreaVo) {
        ScenicArea scenicArea = new ScenicArea();
        scenicArea.setId(scenicAreaVo.getId());
        scenicArea.setScenicName(scenicAreaVo.getScenicName());
        scenicArea.setOpenTime(scenicAreaVo.getOpenTime());
        scenicArea.setGeneralSituation(scenicAreaVo.getGeneralSituation());
        scenicArea.setVoice(scenicAreaVo.getVoice());
        scenicArea.setCover(scenicAreaVo.getCover());
        scenicArea.setUpdateTime(new Date());
        int result;
        result = scenicAreaMapper.updateByPrimaryKeySelective(scenicArea);
        if (result == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return HttpResult.retCol(-1);
        }
        for (int i = 0; i < scenicAreaVo.getScenicPhotos().size(); i++) {
            result = scenicPhotoMapper.updateByPrimaryKeySelective(scenicAreaVo.getScenicPhotos().get(i));
            if (result == 0) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return HttpResult.retCol(-1);
            }
        }
        return HttpResult.retCol(result);
    }

    @Override
    public HttpResult selectAllScenicArea(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        ScenicAreaCriteria example = new ScenicAreaCriteria();
        List<ScenicArea> list = scenicAreaMapper.selectByExample(example);
        PageInfo<ScenicArea> pageInfo = new PageInfo<>(list);
        return HttpResult.success(pageInfo);
    }
}
