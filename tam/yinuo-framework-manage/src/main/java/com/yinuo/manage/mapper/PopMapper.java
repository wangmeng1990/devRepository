package com.yinuo.manage.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.inuol.entity.Pop;
import com.inuol.entity.PopCriteria;

public interface PopMapper {
    long countByExample(PopCriteria example);

    int deleteByExample(PopCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(Pop record);

    int insertSelective(Pop record);

    List<Pop> selectByExample(PopCriteria example);

    Pop selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Pop record, @Param("example") PopCriteria example);

    int updateByExample(@Param("record") Pop record, @Param("example") PopCriteria example);

    int updateByPrimaryKeySelective(Pop record);

    int updateByPrimaryKey(Pop record);
}