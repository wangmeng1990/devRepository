package com.ark.hngxt.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ark.hngxt.product.domain.EntpProductMatch;
import com.ark.hngxt.product.model.ProductIntelligentMatchVO;
import com.baomidou.mybatisplus.mapper.BaseMapper;

public interface EntpProductMatchMapper extends BaseMapper<EntpProductMatch>{

	/**根据当前企业查询智能匹配产品
	 * @param entpId
	 * @return
	 */
	List<ProductIntelligentMatchVO> queryMatchProducList(@Param("entpId") Long entpId);

}
