package com.yinuo.scenic.service.impl;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinuo.common.common.HttpResult;
import com.inuol.entity.scenic.Channel;
import com.inuol.entity.scenic.ChannelCriteria;
import com.inuol.vo.scenic.ChannelVo;
import com.yinuo.scenic.mapper.ChannelMapper;
import com.yinuo.scenic.service.ChannelService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Capejor
 * @date 2020-01-06 18:11
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelMapper channelMapper;

    @Override
    public HttpResult insertChannel(ChannelVo vo) {
        Channel channel = new Channel();
        BeanUtils.copyProperties(vo,channel);
        channel.setId(IdWorker.getId());
        channel.setCreateTime(new Date());
        channel.setCreator(1L);
        return HttpResult.retCol(channelMapper.insertSelective(channel));
    }

    @Override
    public HttpResult updateChannel(ChannelVo vo) {
        Channel channel = new Channel();
        BeanUtils.copyProperties(vo,channel);
        channel.setUpdateTime(new Date());
        return HttpResult.retCol(channelMapper.updateByPrimaryKeySelective(channel));
    }

    @Override
    public HttpResult selectAllChannel(Integer pageNum, Integer pageSize, String name, String code) {
        PageHelper.startPage(pageNum,pageSize);
        ChannelCriteria channelCriteria = new ChannelCriteria();
        channelCriteria.createCriteria().andNameLike("%"+name+"%").andCodeLike("%"+code+"%");
        List<Channel> list = channelMapper.selectByExample(channelCriteria);
        PageInfo<Channel> pageInfo = new PageInfo<>(list);
        return HttpResult.success(pageInfo);
    }

    @Override
    public HttpResult deleteOneById(Long id) {
        Channel channel = new Channel();
        channel.setId(id);
        channel.setState("0");
        return HttpResult.retCol(channelMapper.updateByPrimaryKeySelective(channel));
    }

    @Override
    public HttpResult updateChannelState(Long id, String state) {
        Channel channel = new Channel();
        channel.setId(id);
        channel.setState(state);
        return HttpResult.retCol(channelMapper.updateByPrimaryKeySelective(channel));
    }


}
