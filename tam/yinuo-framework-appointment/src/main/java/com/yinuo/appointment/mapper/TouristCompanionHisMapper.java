package com.yinuo.appointment.mapper;

import com.inuol.entity.TouristCompanionHis;
import com.inuol.entity.TouristCompanionHisCriteria;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TouristCompanionHisMapper {
    long countByExample(TouristCompanionHisCriteria example);

    int deleteByExample(TouristCompanionHisCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(TouristCompanionHis record);

    int insertSelective(TouristCompanionHis record);

    List<TouristCompanionHis> selectByExample(TouristCompanionHisCriteria example);

    TouristCompanionHis selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TouristCompanionHis record, @Param("example") TouristCompanionHisCriteria example);

    int updateByExample(@Param("record") TouristCompanionHis record, @Param("example") TouristCompanionHisCriteria example);

    int updateByPrimaryKeySelective(TouristCompanionHis record);

    int updateByPrimaryKey(TouristCompanionHis record);
}