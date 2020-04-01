package com.yinuo.appointment.mapper;

import com.inuol.entity.TouristCompanion;
import com.inuol.entity.TouristCompanionCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TouristCompanionMapper {
    long countByExample(TouristCompanionCriteria example);

    int deleteByExample(TouristCompanionCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(TouristCompanion record);

    int insertSelective(TouristCompanion record);

    List<TouristCompanion> selectByExample(TouristCompanionCriteria example);

    TouristCompanion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TouristCompanion record, @Param("example") TouristCompanionCriteria example);

    int updateByExample(@Param("record") TouristCompanion record, @Param("example") TouristCompanionCriteria example);

    int updateByPrimaryKeySelective(TouristCompanion record);

    int updateByPrimaryKey(TouristCompanion record);
}