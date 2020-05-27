package com.ark.hngxt.product.mapper;

import com.ark.hngxt.product.domain.FeedBack;
import com.ark.hngxt.product.domain.FeedBackCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FeedBackMapper {
    long countByExample(FeedBackCriteria example);

    int deleteByExample(FeedBackCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(FeedBack record);

    int insertSelective(FeedBack record);

    List<FeedBack> selectByExample(FeedBackCriteria example);

    FeedBack selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FeedBack record, @Param("example") FeedBackCriteria example);

    int updateByExample(@Param("record") FeedBack record, @Param("example") FeedBackCriteria example);

    int updateByPrimaryKeySelective(FeedBack record);

    int updateByPrimaryKey(FeedBack record);
}