package com.yinuo.manage.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.inuol.entity.Subject;
import com.inuol.entity.SubjectCriteria;

public interface SubjectMapper {
    long countByExample(SubjectCriteria example);

    int deleteByExample(SubjectCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(Subject record);

    int insertSelective(Subject record);

    List<Subject> selectByExample(SubjectCriteria example);

    Subject selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Subject record, @Param("example") SubjectCriteria example);

    int updateByExample(@Param("record") Subject record, @Param("example") SubjectCriteria example);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);
}