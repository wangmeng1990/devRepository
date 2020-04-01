package com.yinuo.manage.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.inuol.entity.Information;
import com.inuol.entity.InformationCriteria;
import com.inuol.entity.InformationWithBLOBs;

public interface InformationMapper {
    long countByExample(InformationCriteria example);

    int deleteByExample(InformationCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(InformationWithBLOBs record);

    int insertSelective(InformationWithBLOBs record);

    List<InformationWithBLOBs> selectByExampleWithBLOBs(InformationCriteria example);

    List<Information> selectByExample(InformationCriteria example);

    InformationWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InformationWithBLOBs record, @Param("example") InformationCriteria example);

    int updateByExampleWithBLOBs(@Param("record") InformationWithBLOBs record, @Param("example") InformationCriteria example);

    int updateByExample(@Param("record") Information record, @Param("example") InformationCriteria example);

    int updateByPrimaryKeySelective(InformationWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(InformationWithBLOBs record);

    int updateByPrimaryKey(Information record);
}