package com.yinuo.appointment.mapper;

import com.inuol.entity.AppointmentDate;
import com.inuol.entity.AppointmentDateCriteria;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AppointmentDateMapper {
    long countByExample(AppointmentDateCriteria example);

    int deleteByExample(AppointmentDateCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(AppointmentDate record);

    int insertSelective(AppointmentDate record);

    List<AppointmentDate> selectByExample(AppointmentDateCriteria example);

    AppointmentDate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AppointmentDate record, @Param("example") AppointmentDateCriteria example);

    int updateByExample(@Param("record") AppointmentDate record, @Param("example") AppointmentDateCriteria example);

    int updateByPrimaryKeySelective(AppointmentDate record);

    int updateByPrimaryKey(AppointmentDate record);


	List<Map<String, Object>> queryDateInfo(Map<String, Object> paramMap);

	List<Map<String, Object>> queryScenicAppointment(Map<String, Object> paramMap);
}