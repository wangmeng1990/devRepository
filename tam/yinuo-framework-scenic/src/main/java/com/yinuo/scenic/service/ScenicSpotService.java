package com.yinuo.scenic.service;

import com.inuol.vo.scenic.ScenicSpotVo;
import com.yinuo.common.common.HttpResult;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Capejor
 * @date 2020-01-21 10:54
 */
public interface ScenicSpotService {

    HttpResult insertScenicSpot(ScenicSpotVo vo);

    HttpResult updateScenicSpot(ScenicSpotVo vo);

    HttpResult selectAllScenicSpot(Integer pageNum,Integer pageSize,Long scenicAreaId,String name);

    HttpResult deleteScenicSpotById(Long id);

}
