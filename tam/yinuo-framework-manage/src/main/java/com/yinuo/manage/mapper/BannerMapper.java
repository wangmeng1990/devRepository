package com.yinuo.manage.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.inuol.entity.Banner;
import com.inuol.entity.BannerCriteria;

public interface BannerMapper {
    long countByExample(BannerCriteria example);

    int deleteByExample(BannerCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(Banner record);

    int insertSelective(Banner record);

    List<Banner> selectByExample(BannerCriteria example);

    Banner selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Banner record, @Param("example") BannerCriteria example);

    int updateByExample(@Param("record") Banner record, @Param("example") BannerCriteria example);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);
}