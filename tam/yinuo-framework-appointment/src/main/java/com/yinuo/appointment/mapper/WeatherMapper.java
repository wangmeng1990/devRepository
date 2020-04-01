package com.yinuo.appointment.mapper;

import com.inuol.entity.Weather;
import com.inuol.entity.WeatherCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeatherMapper {
    long countByExample(WeatherCriteria example);

    int deleteByExample(WeatherCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(Weather record);

    int insertSelective(Weather record);

    List<Weather> selectByExample(WeatherCriteria example);

    Weather selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Weather record, @Param("example") WeatherCriteria example);

    int updateByExample(@Param("record") Weather record, @Param("example") WeatherCriteria example);

    int updateByPrimaryKeySelective(Weather record);

    int updateByPrimaryKey(Weather record);
}