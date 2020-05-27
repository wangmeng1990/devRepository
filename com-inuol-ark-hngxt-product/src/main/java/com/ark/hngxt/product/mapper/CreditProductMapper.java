package com.ark.hngxt.product.mapper;

import com.ark.hngxt.product.domain.CreditProduct;
import com.ark.hngxt.product.domain.CreditProductCriteria;
import com.ark.hngxt.product.domain.CreditProductWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CreditProductMapper {
    long countByExample(CreditProductCriteria example);

    int deleteByExample(CreditProductCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(CreditProductWithBLOBs record);

    int insertSelective(CreditProductWithBLOBs record);

    List<CreditProductWithBLOBs> selectByExampleWithBLOBs(CreditProductCriteria example);

    List<CreditProduct> selectByExample(CreditProductCriteria example);

    CreditProductWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CreditProductWithBLOBs record, @Param("example") CreditProductCriteria example);

    int updateByExampleWithBLOBs(@Param("record") CreditProductWithBLOBs record, @Param("example") CreditProductCriteria example);

    int updateByExample(@Param("record") CreditProduct record, @Param("example") CreditProductCriteria example);

    int updateByPrimaryKeySelective(CreditProductWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CreditProductWithBLOBs record);

    int updateByPrimaryKey(CreditProduct record);
}