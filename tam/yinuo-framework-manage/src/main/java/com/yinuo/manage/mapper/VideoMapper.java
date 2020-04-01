package com.yinuo.manage.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.inuol.entity.Video;
import com.inuol.entity.VideoCriteria;

public interface VideoMapper {
    long countByExample(VideoCriteria example);

    int deleteByExample(VideoCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(Video record);

    int insertSelective(Video record);

    List<Video> selectByExampleWithBLOBs(VideoCriteria example);

    List<Video> selectByExample(VideoCriteria example);

    Video selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Video record, @Param("example") VideoCriteria example);

    int updateByExampleWithBLOBs(@Param("record") Video record, @Param("example") VideoCriteria example);

    int updateByExample(@Param("record") Video record, @Param("example") VideoCriteria example);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKeyWithBLOBs(Video record);

    int updateByPrimaryKey(Video record);
}