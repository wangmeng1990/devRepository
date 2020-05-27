package com.ark.hngxt.product.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ark.hngxt.product.domain.FinanceProduct;
import com.ark.hngxt.product.domain.FinanceProductCriteria;
import com.ark.hngxt.product.domain.FinanceProductWithBLOBs;
import com.ark.hngxt.product.model.ProductCommonVO;

public interface FinanceProductMapper {
    long countByExample(FinanceProductCriteria example);

    int deleteByExample(FinanceProductCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(FinanceProductWithBLOBs record);

    int insertSelective(FinanceProductWithBLOBs record);

    List<FinanceProductWithBLOBs> selectByExampleWithBLOBs(FinanceProductCriteria example);

    List<FinanceProduct> selectByExample(FinanceProductCriteria example);

    FinanceProductWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FinanceProductWithBLOBs record, @Param("example") FinanceProductCriteria example);

    int updateByExampleWithBLOBs(@Param("record") FinanceProductWithBLOBs record, @Param("example") FinanceProductCriteria example);

    int updateByExample(@Param("record") FinanceProduct record, @Param("example") FinanceProductCriteria example);

    int updateByPrimaryKeySelective(FinanceProductWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(FinanceProductWithBLOBs record);

    int updateByPrimaryKey(FinanceProduct record);

	List<FinanceProductWithBLOBs> queryFinanceProductMarket(Map<String, Object> queryMap);

	List<ProductCommonVO> getAllProduct();
}