package com.ark.hngxt.product.mapper;

import com.ark.hngxt.product.domain.RecommendOrganization;
import com.ark.hngxt.product.domain.RecommendOrganizationCriteria;
import com.ark.hngxt.product.model.RecommendOrganizationVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RecommendOrganizationMapper {
    long countByExample(RecommendOrganizationCriteria example);

    int deleteByExample(RecommendOrganizationCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(RecommendOrganization record);

    int insertSelective(RecommendOrganization record);

    List<RecommendOrganization> selectByExample(RecommendOrganizationCriteria example);

    RecommendOrganization selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RecommendOrganization record, @Param("example") RecommendOrganizationCriteria example);

    int updateByExample(@Param("record") RecommendOrganization record, @Param("example") RecommendOrganizationCriteria example);

    int updateByPrimaryKeySelective(RecommendOrganization record);

    int updateByPrimaryKey(RecommendOrganization record);

	List<RecommendOrganizationVO> queryOrganizationRecommendList(Map<String, Object> map);

	String orgProductInterestRateRang(Map<String, Object> map);

	List<RecommendOrganizationVO> queryOrganizationRecommendList_1(Map<String, Object> map);

	List<RecommendOrganizationVO> queryHotOrganizationRecommendList(Map<String, Object> map);

	Map<String,Object> getOrgProductInfo(@Param("id") Long id);
}