package com.yinuo.bigdata.system.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.inuol.bigdata.TBigdataMessage;
import com.yinuo.bigdata.system.mapper.MessageMapper;
import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ：jias
 * @date ：2020/1/15 16:22
 */
@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;

    /**
     * 条件查询消息内容
     * @param page
     * @param size
     * @param message
     * @return
     */
    public Page<TBigdataMessage> findMyMessage(int page, int size,TBigdataMessage message){
        PageHelper.startPage(page, size);
        Example example =new Example(TBigdataMessage.class);
        Example.Criteria criteria = example.createCriteria();
        if (message.getIsRead()!=null){
            criteria.andEqualTo("isRead",message.getIsRead());
        }
        //criteria.andEqualTo("belongsId",message.getBelongsId());
        List<TBigdataMessage> tBigdataMessages = messageMapper.selectByExample(example);
        return (Page<TBigdataMessage>)tBigdataMessages;
    }

    public int updateMessageType(Long[] ids){
        return messageMapper.updateMessageType(ids);
    }

    public int deleteBatchMessage(Long[] ids){
        return messageMapper.deleteBatchMessage(ids);
    }

}
