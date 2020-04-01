package com.yinuo.appointment.mapper;

import com.inuol.entity.AppointmentDateItem;
import com.inuol.entity.AppointmentDateItemCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppointmentDateItemMapper {
    long countByExample(AppointmentDateItemCriteria example);

    int deleteByExample(AppointmentDateItemCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(AppointmentDateItem record);

    int insertSelective(AppointmentDateItem record);

    List<AppointmentDateItem> selectByExample(AppointmentDateItemCriteria example);

    AppointmentDateItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AppointmentDateItem record, @Param("example") AppointmentDateItemCriteria example);

    int updateByExample(@Param("record") AppointmentDateItem record, @Param("example") AppointmentDateItemCriteria example);

    int updateByPrimaryKeySelective(AppointmentDateItem record);

    int updateByPrimaryKey(AppointmentDateItem record);
}