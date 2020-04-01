package com.yinuo.scenic.service;

import com.yinuo.common.common.HttpResult;
import com.inuol.vo.scenic.ScenicAreaVo;

/**
 * @author Capejor
 * @date 2019-12-27 11:34
 */

public interface ScenicAreaService {

    HttpResult updateScenicArea(ScenicAreaVo scenicAreaVo);

    HttpResult selectAllScenicArea(Integer pageNum, Integer pageSize);

}
