package com.yinuo.manage.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.inuol.entity.Notice;
import com.inuol.entity.NoticeCriteria;

public interface NoticeMapper {
    long countByExample(NoticeCriteria example);

    int deleteByExample(NoticeCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(Notice record);

    int insertSelective(Notice record);

    List<Notice> selectByExampleWithBLOBs(NoticeCriteria example);

    List<Notice> selectByExample(NoticeCriteria example);

    Notice selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Notice record, @Param("example") NoticeCriteria example);

    int updateByExampleWithBLOBs(@Param("record") Notice record, @Param("example") NoticeCriteria example);

    int updateByExample(@Param("record") Notice record, @Param("example") NoticeCriteria example);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKeyWithBLOBs(Notice record);

    int updateByPrimaryKey(Notice record);
}