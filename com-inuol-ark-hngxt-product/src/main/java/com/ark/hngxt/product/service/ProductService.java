package com.ark.hngxt.product.service;

import java.util.List;
import java.util.Map;

import com.ark.dependencies.common.auth.model.CurrentUser;
import com.ark.dependencies.common.auth.model.ListResult;
import com.ark.hngxt.product.model.AddCreditProductVO;
import com.ark.hngxt.product.model.AddFeedBackAppVO;
import com.ark.hngxt.product.model.AddFeedBackVO;
import com.ark.hngxt.product.model.AddFinanceProductVO;
import com.ark.hngxt.product.model.CreditProductVO;
import com.ark.hngxt.product.model.FeedBackVO;
import com.ark.hngxt.product.model.FinanceProductVO;
import com.ark.hngxt.product.model.HotProductVO;
import com.ark.hngxt.product.model.ProductApplyCountDTO;
import com.ark.hngxt.product.model.ProductCommonVO;
import com.ark.hngxt.product.model.ProductOrganizationTypeDTO;
import com.ark.hngxt.product.model.ProductVO;
import com.ark.hngxt.product.model.QueryCreditProduct;
import com.ark.hngxt.product.model.QueryFeedBack;
import com.ark.hngxt.product.model.QueryFinanceProduct;
import com.ark.hngxt.product.model.QueryOrganizationRecommend;
import com.ark.hngxt.product.model.QueryProductCount;
import com.ark.hngxt.product.model.RecommendOrganizationVO;
import com.ark.hngxt.product.model.ScreeningConditionsItem;
import com.ark.hngxt.product.model.ScreeningConditionsVO;
import com.ark.hngxt.product.model.UpdateCreditProductVO;
import com.ark.hngxt.product.model.UpdateFinanceProductVO;
import com.ark.hngxt.product.model.UpdateStrategySetVO;
import com.ark.hngxt.product.model.intelligent.match.ProductToBigData;

public interface ProductService {


	/**新增金融产品
	 * @param currentUser
	 * @param product
	 * @return
	 */
	Long addFinanceProduct(CurrentUser currentUser, AddFinanceProductVO product);

	/**新增特色产品
	 * @param currentUser
	 * @param product
	 * @return
	 */
	Long addCreditProduct(CurrentUser currentUser, AddCreditProductVO product);

	/**更新金融产品
	 * @param currentUser
	 * @param updateProduct
	 */
	void updateFinanceProduct(CurrentUser currentUser, UpdateFinanceProductVO updateProduct);

	/**更新特色产品
	 * @param currentUser
	 * @param updateProduct
	 */
	void updateCreditProduct(CurrentUser currentUser, UpdateCreditProductVO updateProduct);

	/**根据id查询金融产品
	 * @param id
	 * @return
	 */
	FinanceProductVO queryFinanceProductById(Long id);

	/**根据id查询特色产品
	 * @param id
	 * @return
	 */
	CreditProductVO queryCreditProductById(Long id);

	/**更新特色产品状态
	 * @param currentUser
	 * @param id
	 * @param status
	 */
	void updateCreditProductStatus(CurrentUser currentUser,Long id, int status);

	/**更新金融产品状态
	 * @param currentUser
	 * @param id
	 * @param status
	 */
	void updateFinanceProductStatus(CurrentUser currentUser, Long id, int status);

	/**条件分页查询金融产品列表
	 * @param queryParam
	 * @return
	 */
	ListResult<FinanceProductVO> queryFinanceProductList(QueryFinanceProduct queryParam);

	/**条件分页查询特色产品列表
	 * @param queryParam
	 * @return
	 */
	ListResult<CreditProductVO> queryCreditProductList(QueryCreditProduct queryParam);

	/**pc端金融超市-查询金融产品列表
	 * @param queryParam
	 * @return
	 */
	ListResult<FinanceProductVO> queryFinanceProductMarket(QueryFinanceProduct queryParam);

	/**查询机构的产品数量
	 * @param queryParam
	 * @return
	 */
	Integer queryOrganizationAllProductList(QueryProductCount queryParam);

	/**设置特色产品策略集
	 * @param currentUser
	 * @param updateStrategySetVo
	 */
	void updateCreditProductStrategySet(CurrentUser currentUser, UpdateStrategySetVO updateStrategySetVo);

	/**设置金融产品策略集
	 * @param currentUser
	 * @param updateStrategySetVo
	 */
	void updateFinanceProductStrategySet(CurrentUser currentUser, UpdateStrategySetVO updateStrategySetVo);

	/**查询所有产品名称
	 * @return
	 */
	List<String> queryAllProductNameList();

	/**热门产品设置-特色产品
	 * @param currentUser
	 * @param hotCreditProductVo
	 */
	void updateHotCreditProduct(CurrentUser currentUser, HotProductVO hotCreditProductVo);

	/**热门产品设置-金融产品
	 * @param currentUser
	 * @param hotProductVo
	 */
	void updateHotFinanceProduct(CurrentUser currentUser, HotProductVO hotProductVo);

	/**pc端我要贷款-产品列表
	 * @return
	 */
	List<ProductVO> willLoanCreditProductList();

	/**条件查询特色产品-不分页
	 * @param queryParam
	 * @return
	 */
	List<CreditProductVO> queryCreditProductListWithOutPage(QueryCreditProduct queryParam);

	/**新增意见反馈
	 * @param currentUser
	 * @param feedBack
	 */
	void addFeedBack(CurrentUser currentUser, AddFeedBackVO feedBack);

	/**条件查询
	 * @param queryParam
	 * @return
	 */
	ListResult<FeedBackVO> queryFeedBackList(QueryFeedBack queryParam);

	/**根据反馈记录id查询意见反馈详情
	 * @param id
	 * @return
	 */
	FeedBackVO queryFeedBackById(Long id);

	/**更新产品表被申请数
	 * @param dto
	 */
	void updateProductApplyCount(ProductApplyCountDTO dto);

	/**查询推荐机构列表
	 * @param queryParam
	 * @return
	 */
	ListResult<RecommendOrganizationVO> queryOrganizationRecommendList(QueryOrganizationRecommend queryParam);

	/**查询企业申请过的机构
	 * @param queryParam
	 * @return
	 */
	RecommendOrganizationVO applyOrganizationHis(QueryOrganizationRecommend queryParam);

	/**根据机构id获取机构详情
	 * @param organizationId
	 * @return
	 */
	RecommendOrganizationVO getOrganizationRecommend(Long organizationId);

	/**获取产品当前状态
	 * @param id
	 * @param type
	 * @return
	 */
	Integer getProductStatus(Long id, String type);

	/**获取所有上架产品
	 * @return
	 */
	List<ProductCommonVO> getAllProduct();

	/**查询热门机构列表
	 * @param queryParam
	 * @return
	 */
	ListResult<RecommendOrganizationVO> queryHotOrganizationRecommendList(QueryOrganizationRecommend queryParam);

	/**获取金融产品查询-筛选条件
	 * @param terminal 1:app2:pc
	 * @return
	 */
	List<ScreeningConditionsVO> financeProductScreeningConditionsList(String terminal);

	/**获取机构的产品发布数，产品利率区间等信息
	 * @param id
	 * @return
	 */
	Map<String,Object> getOrgProductInfo(Long id);

	/**app新增意见反馈
	 * @param currentUser
	 * @param feedBack
	 */
	void addFeedBackApp(CurrentUser currentUser, AddFeedBackAppVO feedBack);

	/**app新增意见反馈-针对对象参数列表
	 * @param code
	 * @return
	 */
	List<ScreeningConditionsItem> feedBackDictList(String code);

	/**产品信息推送到大数据端
	 * @param product
	 * @return
	 */
	String pushProduct(ProductToBigData product);

	void updateProductApplyAmount(ProductApplyCountDTO dto);

	void updateFinanceProductOrganizationType(ProductOrganizationTypeDTO dto);
}
