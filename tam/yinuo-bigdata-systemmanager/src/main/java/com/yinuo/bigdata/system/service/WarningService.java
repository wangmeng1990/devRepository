package com.yinuo.bigdata.system.service;

import com.github.pagehelper.PageHelper;
import com.inuol.bigdata.BigdataWarning;
import com.inuol.bigdata.ext.BigdataWarningNode;
import com.inuol.user.User;
import com.yinuo.api.user.UserApi;
import com.yinuo.bigdata.system.mapper.WarningMapper;
import com.yinuo.bigdata.system.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * 预警列表
 * @author ：jias
 *  * @date ：2020/2/11 10:31
 */
@Service
public class WarningService {

    @Autowired
    private WarningMapper warningMapper;

    @Autowired
    private UserApi userApi;

    /**
     * 条件查询预警
     * @param page
     * @param size
     * @param bigdataWarningNode
     * @return
     */
    public List<BigdataWarning> findAll(int page, int size, BigdataWarningNode bigdataWarningNode){
        PageHelper.startPage(page,size);
        Example example = new Example(BigdataWarning.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(bigdataWarningNode.getWarningSort())){
            criteria.andEqualTo("warningSort", bigdataWarningNode.getWarningSort());
        }
        if (StringUtils.isNotBlank(bigdataWarningNode.getTimeBegin())) {
            criteria.andGreaterThanOrEqualTo("timeBegin", DateUtil.getTodaycreateTime(bigdataWarningNode.getTimeBegin()));
        }
        if (StringUtils.isNotBlank(bigdataWarningNode.getTimeEnd())) {
            criteria.andLessThanOrEqualTo("timeEnd", DateUtil.getTodayLastTime(bigdataWarningNode.getTimeEnd()));
        }
        if (StringUtils.isNotBlank(bigdataWarningNode.getKeyWord())) {
            Example.Criteria orCriteria = example.createCriteria();
            orCriteria.orLike("reportPersonName","%"+bigdataWarningNode.getKeyWord()+"%")
                    .orLike("warningNumber","%"+bigdataWarningNode.getKeyWord()+"%");
            example.and(orCriteria);
        }
        List<BigdataWarning> warningList = warningMapper.selectByExample(example);
        return warningList;
    }

    /**
     * 应急救助上报
     * @param bigdataWarning
     * @return
     */
    public int reported(String id, BigdataWarning bigdataWarning){
        User user = userApi.getUserById(Long.valueOf(id));
        if (user!=null) {
            bigdataWarning.setReportPersonName(user.getRealityName());
        }
        bigdataWarning.setReportTime(new Date());
        return warningMapper.insertSelective(bigdataWarning);
    }



}
