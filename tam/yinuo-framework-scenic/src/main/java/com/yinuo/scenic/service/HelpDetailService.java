package com.yinuo.scenic.service;

import com.yinuo.common.common.HttpResult;
import com.inuol.vo.scenic.HelpDetailVo;

/**
 * @author Capejor
 * @date 2019-12-31 13:44
 */
public interface HelpDetailService {

    HttpResult insertHelpDetail(HelpDetailVo vo);

    HttpResult updateHelpDetail(HelpDetailVo vo);

    HttpResult selectAllHelpDetail(Integer pageNum, Integer pageSize, String question);

    HttpResult deleteOneById(Long id);

    HttpResult updateHelpDetailState(Long id, String state);
}
