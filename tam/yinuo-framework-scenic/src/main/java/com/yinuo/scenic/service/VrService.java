package com.yinuo.scenic.service;

import com.inuol.vo.scenic.VrVo;
import com.yinuo.common.common.HttpResult;

/**
 * @author Capejor
 * @date 2020-01-22 15:31
 */
public interface VrService {

    HttpResult insertVrDescription(VrVo vo);

    HttpResult insertVrPhoto(VrVo vo);

}
