package com.yinuo.manage.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.inuol.entity.CulturalCreation;
import com.inuol.entity.CulturalCreationCriteria;

public interface CulturalCreationMapper {
    long countByExample(CulturalCreationCriteria example);

    int deleteByExample(CulturalCreationCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(CulturalCreation record);

    int insertSelective(CulturalCreation record);

    List<CulturalCreation> selectByExample(CulturalCreationCriteria example);

    CulturalCreation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CulturalCreation record, @Param("example") CulturalCreationCriteria example);

    int updateByExample(@Param("record") CulturalCreation record, @Param("example") CulturalCreationCriteria example);

    int updateByPrimaryKeySelective(CulturalCreation record);

    int updateByPrimaryKey(CulturalCreation record);
}