package com.yinuo.manage.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.inuol.entity.Function;
import com.inuol.entity.FunctionCriteria;

public interface FunctionMapper {
    long countByExample(FunctionCriteria example);

    int deleteByExample(FunctionCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(Function record);

    int insertSelective(Function record);

    List<Function> selectByExample(FunctionCriteria example);

    Function selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Function record, @Param("example") FunctionCriteria example);

    int updateByExample(@Param("record") Function record, @Param("example") FunctionCriteria example);

    int updateByPrimaryKeySelective(Function record);

    int updateByPrimaryKey(Function record);
}