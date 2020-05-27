package com.ark.hngxt.product.service;

import java.util.List;

import com.ark.dependencies.common.auth.model.CurrentUser;
import com.ark.hngxt.product.domain.MatchProduct;
import com.ark.hngxt.product.model.ProductIntelligentMatchVO;
import com.baomidou.mybatisplus.service.IService;

public interface MatchProductService extends IService<MatchProduct>{

	/**1pc端2app
	 * @param currentUser
	 * @param type
	 * @return
	 */
	List<ProductIntelligentMatchVO> queryProductIntelligentMatchList(CurrentUser currentUser,String type);

}
