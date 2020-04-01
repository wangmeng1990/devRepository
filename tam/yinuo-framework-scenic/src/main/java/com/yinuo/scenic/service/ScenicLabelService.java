package com.yinuo.scenic.service;

import com.inuol.vo.scenic.ScenicLabelVo;
import com.yinuo.common.common.HttpResult;

/**
 * @author Capejor
 * @date 2020-02-11 11:50
 */
public interface ScenicLabelService {

    HttpResult insertScenicLabel(ScenicLabelVo vo);

    HttpResult updateScenicLabel(ScenicLabelVo vo);

    HttpResult selectAllScenicLabel(int pageNum, int pageSize, String labelName);

    HttpResult deleteOneById(long id);
}
