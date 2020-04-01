package com.yinuo.scenic.service;

import com.yinuo.common.common.HttpResult;
import com.inuol.vo.scenic.TourismHelpVo;

/**
 * @author Capejor
 * @date 2019-12-30 16:48
 */
public interface TourismHelpService {

    HttpResult insertTourismHelp(TourismHelpVo vo);

    HttpResult updateTourismHelp(TourismHelpVo vo);

    HttpResult selectAllTourismHelp(Integer pageNum, Integer pageSize, String questionType);

    HttpResult deleteOneById(Long id);

    HttpResult updateTourismHelpState(Long id,String state);
}
