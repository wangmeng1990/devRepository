package com.yinuo.scenic.service;

import com.yinuo.common.common.HttpResult;
import com.inuol.vo.scenic.ChannelVo;

/**
 * @author Capejor
 * @date 2020-01-06 17:31
 */
public interface ChannelService {

    HttpResult insertChannel(ChannelVo vo);

    HttpResult updateChannel(ChannelVo vo);

    HttpResult selectAllChannel(Integer pageNum, Integer pageSize, String name, String code);

    HttpResult deleteOneById(Long id);

    HttpResult updateChannelState(Long id, String state);


}
