package com.yinuo.manage.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.inuol.entity.Message;
import com.inuol.entity.MessageCriteria;

public interface MessageMapper {
    long countByExample(MessageCriteria example);

    int deleteByExample(MessageCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(Message record);

    int insertSelective(Message record);

    List<Message> selectByExampleWithBLOBs(MessageCriteria example);

    List<Message> selectByExample(MessageCriteria example);

    Message selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Message record, @Param("example") MessageCriteria example);

    int updateByExampleWithBLOBs(@Param("record") Message record, @Param("example") MessageCriteria example);

    int updateByExample(@Param("record") Message record, @Param("example") MessageCriteria example);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKeyWithBLOBs(Message record);

    int updateByPrimaryKey(Message record);
}