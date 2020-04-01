package com.yinuo.appointment.mapper;

import com.inuol.entity.TouristAppointment;
import com.inuol.entity.TouristAppointmentCriteria;
import com.inuol.vo.AppointmentScenicVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TouristAppointmentMapper {
    long countByExample(TouristAppointmentCriteria example);

    int deleteByExample(TouristAppointmentCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(TouristAppointment record);

    int insertSelective(TouristAppointment record);

    List<TouristAppointment> selectByExample(TouristAppointmentCriteria example);

    TouristAppointment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TouristAppointment record, @Param("example") TouristAppointmentCriteria example);

    int updateByExample(@Param("record") TouristAppointment record, @Param("example") TouristAppointmentCriteria example);

    int updateByPrimaryKeySelective(TouristAppointment record);

    int updateByPrimaryKey(TouristAppointment record);

	List<AppointmentScenicVo> getAppointmentScenic();
}