package com.yinuo.strategy.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inuol.entity.strategy.Label;
import com.yinuo.strategy.mapper.LabelMapper;
import com.yinuo.strategy.service.LabelService;
import org.springframework.stereotype.Service;

@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {
}
