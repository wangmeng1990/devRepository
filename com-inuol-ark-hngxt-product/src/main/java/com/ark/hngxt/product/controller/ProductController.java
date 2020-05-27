package com.ark.hngxt.product.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ark.dependencies.common.auth.model.CurrentUser;
import com.ark.dependencies.common.auth.model.ListResult;
import com.ark.dependencies.common.auth.util.UserContext;
import com.ark.dependencies.common.exception.RestApiException;
import com.ark.hngxt.product.model.AddCreditProductVO;
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
import com.ark.hngxt.product.model.ScreeningConditions;
import com.ark.hngxt.product.model.ScreeningConditionsVO;
import com.ark.hngxt.product.model.UpdateCreditProductVO;
import com.ark.hngxt.product.model.UpdateFinanceProductVO;
import com.ark.hngxt.product.model.UpdateStrategySetVO;
import com.ark.hngxt.product.service.ProductService;
import com.ark.hngxt.product.util.DataUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 产品服务
 * 
 * @author wangmeng
 *
 */

@Api(tags = "pc产品")
@Slf4j
@RestController
@RequestMapping("/")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@ApiOperation(value = "新增首贷金融产品")
	@PostMapping("/addFinanceProduct")
	public ResponseEntity<String> addFinanceProduct(@RequestBody @Validated AddFinanceProductVO product) {
		CurrentUser currentUser = UserContext.currentUser();
		productService.addFinanceProduct(currentUser, product);
		log.info(String.format("用户：%s 增加一首贷金融产品：%s", currentUser.getLoginName(), product.getName()));
		return ResponseEntity.status(HttpStatus.OK).body("添加成功");
	}

	@ApiOperation(value = "新增首贷信用产品")
	@PostMapping("/addCreditProduct")
	public ResponseEntity<String> addCreditProduct(@RequestBody @Validated AddCreditProductVO product) {
		CurrentUser currentUser = UserContext.currentUser();
		productService.addCreditProduct(currentUser, product);
		log.info(String.format("用户：%s 增加一首贷信用产品：%s", currentUser.getLoginName(), product.getName()));
		return ResponseEntity.status(HttpStatus.OK).body("添加成功");
	}

	@ApiOperation(value = "查询首贷金融产品列表-后台管理")
	@GetMapping("/queryFinanceProductList")
	public ResponseEntity<ListResult<FinanceProductVO>> queryFinanceProductList(
			@ApiParam(name = "name", value = "产品名称", required = false) @RequestParam(required = false) String name,
			@ApiParam(name = "organization", value = "服务机构", required = false) @RequestParam(required = false) String organization,
			@ApiParam(name = "createDate", value = "创建时间：yyyy-MM-dd", required = false) @RequestParam(required = false) String createDate,
			@ApiParam(name = "status", value = "状态：1上架0下架", required = false) @RequestParam(required = false) Integer status,
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		QueryFinanceProduct queryParam = new QueryFinanceProduct();
		queryParam.setName(name);
		queryParam.setStatus(status);
		queryParam.setOrganization(organization);
		queryParam.setCreateDate(createDate);
		queryParam.setPageNum(pageNum);
		queryParam.setPageSize(pageSize);
		ListResult<FinanceProductVO> page = productService.queryFinanceProductList(queryParam);
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}

	@ApiOperation(value = "查询首贷信用产品列表-后台管理")
	@GetMapping("/queryCreditProductList")
	public ResponseEntity<ListResult<CreditProductVO>> queryCreditProductList(
			@ApiParam(name = "name", value = "产品名称", required = false) @RequestParam(required = false) String name,
			@ApiParam(name = "status", value = "状态：1上架0下架", required = false) @RequestParam(required = false) Integer status,
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		QueryCreditProduct queryParam = new QueryCreditProduct();
		queryParam.setName(name);
		queryParam.setStatus(status);
		queryParam.setPageNum(pageNum);
		queryParam.setPageSize(pageSize);
		ListResult<CreditProductVO> page = productService.queryCreditProductList(queryParam);
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}

	@ApiOperation(value = "修改首贷金融产品")
	@PatchMapping("/updateFinanceProduct")
	public ResponseEntity<String> updateFinanceProduct(@RequestBody @Validated UpdateFinanceProductVO updateProduct) {
		if (null == updateProduct.getId()) {
			throw new RestApiException("无效的产品");
		}
		CurrentUser currentUser = UserContext.currentUser();
		productService.updateFinanceProduct(currentUser, updateProduct);
		log.info(String.format("用户：%s 修改了首贷金融产品: %d", currentUser.getLoginName(), updateProduct.getId()));
		return ResponseEntity.status(HttpStatus.OK).body("更新成功");
	}

	@ApiOperation(value = "修改首贷信用产品")
	@PatchMapping("/updateCreditProduct")
	public ResponseEntity<String> updateCreditProduct(@RequestBody @Validated UpdateCreditProductVO updateProduct) {
		if (null == updateProduct.getId()) {
			throw new RestApiException("无效的产品");
		}
		CurrentUser currentUser = UserContext.currentUser();
		productService.updateCreditProduct(currentUser, updateProduct);
		log.info(String.format("用户：%s 修改了首贷信用产品: %d", currentUser.getLoginName(), updateProduct.getId()));
		return ResponseEntity.status(HttpStatus.OK).body("更新成功");
	}

	@ApiOperation(value = "设置特色产品策略集")
	@PutMapping("/UpdateCreditProductStrategySet")
	public ResponseEntity<String> updateCreditProductStrategySet(@RequestBody @Validated UpdateStrategySetVO updateStrategySetVo) {
		CurrentUser currentUser = UserContext.currentUser();
		productService.updateCreditProductStrategySet(currentUser, updateStrategySetVo);
		log.info(String.format("用户：%s 修改了首贷特色产品策略集: %d", currentUser.getLoginName(), updateStrategySetVo.getId()));
		return ResponseEntity.status(HttpStatus.OK).body("更新成功");
	}
	
	@ApiOperation(value = "设置金融产品策略集")
	@PutMapping("/UpdateFinanceProductStrategySet")
	public ResponseEntity<String> updateFinanceProductStrategySet(@RequestBody @Validated UpdateStrategySetVO updateStrategySetVo) {
		CurrentUser currentUser = UserContext.currentUser();
		productService.updateFinanceProductStrategySet(currentUser, updateStrategySetVo);
		log.info(String.format("用户：%s 修改了首贷金融产品策略集: %d", currentUser.getLoginName(), updateStrategySetVo.getId()));
		return ResponseEntity.status(HttpStatus.OK).body("更新成功");
	}
	
	@ApiOperation(value = "设置特色产品热门推荐")
	@PutMapping("/UpdateHotCreditProduct")
	public ResponseEntity<String> updateHotCreditProduct(@RequestBody @Validated HotProductVO hotProductVo) {
		CurrentUser currentUser = UserContext.currentUser();
		productService.updateHotCreditProduct(currentUser, hotProductVo);
		return ResponseEntity.status(HttpStatus.OK).body("更新成功");
	}
	
	@ApiOperation(value = "设置金融产品热门推荐")
	@PutMapping("/UpdateHotFinanceProduct")
	public ResponseEntity<String> updateHotFinanceProduct(@RequestBody @Validated HotProductVO hotProductVo) {
		CurrentUser currentUser = UserContext.currentUser();
		productService.updateHotFinanceProduct(currentUser, hotProductVo);
		return ResponseEntity.status(HttpStatus.OK).body("更新成功");
	}
	
	@ApiOperation(value = "根据id查询首贷金融产品")
	@GetMapping("/free/queryFinanceProductById/{id}")
	public ResponseEntity<FinanceProductVO> queryFinanceProductById(@PathVariable(name = "id") Long id) {
		if (id == null || id < 1) {
			throw new RestApiException("无此产品");
		}
		FinanceProductVO product = productService.queryFinanceProductById(id);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}

	@ApiOperation(value = "根据id查询首贷信用产品")
	@GetMapping("/free/queryCreditProductById/{id}")
	public ResponseEntity<CreditProductVO> queryCreditProductById(@PathVariable(name = "id") Long id) {
		if (id == null || id < 1) {
			throw new RestApiException("无此产品");
		}
		CreditProductVO product = productService.queryCreditProductById(id);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}

	@ApiOperation(value = "发布/下架首贷金融产品")
	@PutMapping("/publishOrRemoveFinanceProduct")
	public ResponseEntity<String> publishOrRemoveFinanceProduct(
			@ApiParam(name = "id", value = "产品id", required = true) @RequestParam(required = true) Long id,
			@ApiParam(name = "status", value = "产品状态1发布0下架", required = true) @RequestParam(required = true) int status) {
		CurrentUser currentUser = UserContext.currentUser();
		currentUser.setUserId(1L);
		productService.updateFinanceProductStatus(currentUser, id, status);
		return ResponseEntity.status(HttpStatus.OK).body("操作成功");
	}

	@ApiOperation(value = "发布/下架首贷信用产品")
	@PutMapping("/publishOrRemoveCreditProduct")
	public ResponseEntity<String> publishOrRemoveCreditProduct(
			@ApiParam(name = "id", value = "产品id", required = true) @RequestParam(required = true) Long id,
			@ApiParam(name = "status", value = "产品状态1发布0下架", required = true) @RequestParam(required = true) int status) {
		CurrentUser currentUser = UserContext.currentUser();
		productService.updateCreditProductStatus(currentUser, id, status);
		return ResponseEntity.status(HttpStatus.OK).body("操作成功");
	}

	@ApiOperation(value = "删除首贷金融产品")
	@PutMapping("/delFinanceProduct")
	public ResponseEntity<String> delFinanceProduct(
			@ApiParam(name = "id", value = "产品id", required = true) @RequestParam(required = true) Long id) {
		CurrentUser currentUser = UserContext.currentUser();
		productService.updateFinanceProductStatus(currentUser, id, 3);
		return ResponseEntity.status(HttpStatus.OK).body("操作成功");
	}
	
	@ApiOperation(value = "删除首贷信用产品")
	@PutMapping("/delCreditProduct")
	public ResponseEntity<String> delCreditProduct(
			@ApiParam(name = "id", value = "产品id", required = true) @RequestParam(required = true) Long id) {
		CurrentUser currentUser = UserContext.currentUser();
		productService.updateCreditProductStatus(currentUser, id, 3);
		return ResponseEntity.status(HttpStatus.OK).body("操作成功");
	}
	
	@ApiOperation(value = "融资产品超市-金融产品超市")
	@GetMapping("/free/queryFinanceProductMarket")
	public ResponseEntity<ListResult<FinanceProductVO>> queryFinanceProductMarket(
			@ApiParam(name = "name", value = "产品名称", required = false) @RequestParam(required = false) String name,
			@ApiParam(name = "screeningConditions", value = "筛选条件：根据筛选条件接口返回构造的jsonString,不选条件传空字符串", required = false) @RequestParam(required = false) String screeningConditions,
			@ApiParam(name = "sort", value = "jsonArray字符串：[{field:排序字段（1申请量2周期3利率）,sort:1升序2降序},{}]，综合不传或空数组", required = false) @RequestParam(required = false) String sort,
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		log.info("pc金融产品超市sort:"+sort+";screeningConditions:"+screeningConditions);
		if(DataUtils.isNotEmpty(sort)) {
			try {
				sort=URLDecoder.decode(sort, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				log.error("pc金融产品超市sort转码异常:"+e.getMessage());
			}
		}
		log.info("pc金融产品超市sort:"+sort);
		QueryFinanceProduct queryParam = new QueryFinanceProduct();
		queryParam.setName(name);
		//queryParam.setType(type);
		if(DataUtils.isNotEmpty(screeningConditions)) {
			try {
				ScreeningConditions sc = JSONObject.parseObject(screeningConditions, ScreeningConditions.class);
				if(null!=sc) {
					queryParam.setGuaranteeType(sc.getGuaranteeType());
					queryParam.setLimit(sc.getLimit());
					queryParam.setAmount(sc.getAmount());
					queryParam.setApplyArea(sc.getApplyArea());
					queryParam.setOrganizationType(sc.getOrganizationType());
					queryParam.setOrganizationId(sc.getOrganizationId());	
				}
			}catch(Exception e) {
				log.error("金融超市筛选查询异常："+e.getMessage());
			}
		}
		queryParam.setSort(sort);
		queryParam.setPageNum(pageNum);
		queryParam.setPageSize(pageSize);
		ListResult<FinanceProductVO> page = productService.queryFinanceProductMarket(queryParam);
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}

	
	@ApiOperation(value = "首页-金融产品-大数据智能匹配")
	@GetMapping("/free/queryBigDataFinanceProduct")
	public ResponseEntity<ListResult<FinanceProductVO>> queryBigDataFinanceProduct(
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		QueryFinanceProduct queryParam = new QueryFinanceProduct();
		queryParam.setStatus(1);
		queryParam.setOrderByClause(" interest_rate_start asc");
		queryParam.setBeBigDataHot(1);
		queryParam.setPageNum(pageNum);
		queryParam.setPageSize(pageSize);
		ListResult<FinanceProductVO> page = productService.queryFinanceProductList(queryParam);
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}
	
	@ApiOperation(value = "首页-产品推荐(首贷信用产品)")
	@GetMapping("/free/queryFirstLoanCreditProductList")
	public ResponseEntity<ListResult<CreditProductVO>> queryFirstLoanCreditProductList(
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		QueryCreditProduct queryParam = new QueryCreditProduct();
		queryParam.setStatus(1);
		queryParam.setBeMainHot(1);
		queryParam.setOrderByClause(" sort asc,interest_rate_start asc ");
		queryParam.setPageNum(pageNum);
		queryParam.setPageSize(pageSize);
		ListResult<CreditProductVO> page = productService.queryCreditProductList(queryParam);
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}

	@ApiOperation(value = "首贷中心-特色产品推荐（不分页）")
	@GetMapping("/free/queryLoanCenterCreditProductList")
	public ResponseEntity<List<CreditProductVO>> queryLoanCenterCreditProductList() {
		QueryCreditProduct queryParam = new QueryCreditProduct();
		List<CreditProductVO> list = productService.queryCreditProductListWithOutPage(queryParam);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@ApiOperation(value = "首页-我要贷款-特色产品")
	@GetMapping("/free/willLoanCreditProductList")
	public ResponseEntity<List<ProductVO>> willLoanCreditProductList() {
		List<ProductVO> list = productService.willLoanCreditProductList();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@ApiOperation(value = "大数据征信-产品推荐(首贷信用产品)")
	@GetMapping("/free/queryCreditProductForBigDataList")
	public ResponseEntity<ListResult<CreditProductVO>> queryCreditProductForBigDataList(
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		QueryCreditProduct queryParam = new QueryCreditProduct();
		queryParam.setStatus(1);
		queryParam.setBeHot(1);
		queryParam.setOrderByClause(" sort asc,interest_rate_start asc ");
		queryParam.setPageNum(pageNum);
		queryParam.setPageSize(pageSize);
		ListResult<CreditProductVO> page = productService.queryCreditProductList(queryParam);
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}
	
	@ApiOperation(value = "个人中心-机构产品列表-信用产品")
	@GetMapping("/queryOrganizationCreditProductList")
	public ResponseEntity<ListResult<CreditProductVO>> queryOrganizationCreditProductList(
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		CurrentUser currentUser = UserContext.currentUser();
		QueryCreditProduct queryParam = new QueryCreditProduct();
		queryParam.setStatus(1);
		queryParam.setOrganizationId(currentUser.getInstId());
		queryParam.setOrderByClause("sort asc,interest_rate_start asc ");
		queryParam.setPageNum(pageNum);
		queryParam.setPageSize(pageSize);
		queryParam.setGetRateAndAmount(true);
		ListResult<CreditProductVO> page = productService.queryCreditProductList(queryParam);
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}
	
	@ApiOperation(value = "机构推荐列表-机构详情-查询特色产品")
	@GetMapping("/free/queryOrganizationDetailCreditProductList")
	public ResponseEntity<ListResult<CreditProductVO>> queryOrganizationDetailCreditProductList(
			@ApiParam(name = "organizationId", value = "机构id", required = true) @RequestParam(required = true) Long organizationId,
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		QueryCreditProduct queryParam = new QueryCreditProduct();
		queryParam.setStatus(1);
		queryParam.setParentOrganizationId(organizationId);
		queryParam.setOrderByClause(" sort asc,interest_rate_start asc ");
		queryParam.setPageNum(pageNum);
		queryParam.setPageSize(pageSize);
		ListResult<CreditProductVO> page = productService.queryCreditProductList(queryParam);
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}
	
	@ApiOperation(value = "机构推荐列表-机构详情-金融产品")
	@GetMapping("/free/queryOrganizationDetailFinanceProductList")
	public ResponseEntity<ListResult<FinanceProductVO>> queryOrganizationDetailFinanceProductList(
			@ApiParam(name = "organizationId", value = "机构id", required = true) @RequestParam(required = true) Long organizationId,
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		QueryFinanceProduct queryParam = new QueryFinanceProduct();
		queryParam.setStatus(1);
		queryParam.setParentOrganizationId(organizationId);
		queryParam.setOrderByClause(" interest_rate_start asc ");
		queryParam.setPageNum(pageNum);
		queryParam.setPageSize(pageSize);
		ListResult<FinanceProductVO> page = productService.queryFinanceProductList(queryParam);
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}
	
	@ApiOperation(value = "查询机构的产品数量")
	@GetMapping("/queryOrganizationAllFinanceProductCount")
	public ResponseEntity<Integer> queryOrganizationAllProductList(
			@ApiParam(name = "type", value = "查询类型：1信用产品2金融产品3所有", required = true) @RequestParam(required = true) String type,
			@ApiParam(name = "organizationId", value = "所属机构id", required = false) @RequestParam(required = false) Long organizationId,
			@ApiParam(name = "parentOrganizationId", value = "上级机构id", required = true) @RequestParam(required = true) Long parentOrganizationId) {
		QueryProductCount queryParam = new QueryProductCount();
		queryParam.setOrganizationId(organizationId);
		queryParam.setParentOrganizationId(parentOrganizationId);
		queryParam.setType(type);
		Integer count = productService.queryOrganizationAllProductList(queryParam);
		return ResponseEntity.status(HttpStatus.OK).body(count);
	}
	
	@ApiOperation(value = "查询所有产品名称")
	@GetMapping("/queryAllProductNameList")
	public ResponseEntity<List<String>> queryAllProductNameList() {
		List<String> list = productService.queryAllProductNameList();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
		
	@ApiOperation(value = "个人中心-机构产品列表-金融产品")
	@GetMapping("/queryOrganizationFinanceProductList")
	public ResponseEntity<ListResult<FinanceProductVO>> queryOrganizationFinanceProductList(
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		CurrentUser currentUser = UserContext.currentUser();
		QueryFinanceProduct queryParam = new QueryFinanceProduct();
		queryParam.setStatus(1);
		queryParam.setOrganizationId(currentUser.getInstId().toString());
		queryParam.setOrderByClause(" sort asc,publish_date desc ");
		queryParam.setPageNum(pageNum);
		queryParam.setPageSize(pageSize);
		ListResult<FinanceProductVO> page = productService.queryFinanceProductList(queryParam);
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}
	
	@ApiOperation(value = "金融机构推荐列表-更多")
	@GetMapping("/free/moreOrganizationRecommendList")
	public ResponseEntity<ListResult<RecommendOrganizationVO>> moreOrganizationRecommendList(
			@ApiParam(name = "orgName", value = "机构名称", required = false) @RequestParam(required = false) String orgName,
			@ApiParam(name = "orgType", value = "机构类型：商业银行，小贷公司，其他机构", required = false) @RequestParam(required = false) String orgType,
			@ApiParam(name = "sortField", value = "排序字段:2默认排序3综合评分", required = true) @RequestParam(required = true) String sortField,
			@ApiParam(name = "sort", value = "1升序2降序：sortField==1时需要此值", required = false) @RequestParam(required = false) String sort,
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		QueryOrganizationRecommend queryParam = new QueryOrganizationRecommend();
		queryParam.setSort(sort);
		queryParam.setSortField(sortField);
		queryParam.setOrgName(orgName);
		queryParam.setOrgType(orgType);
		queryParam.setPageNum(pageNum);
		queryParam.setPageSize(pageSize);
		log.info("获取入驻机构queryParam："+queryParam.toString());
		ListResult<RecommendOrganizationVO> page = productService.queryOrganizationRecommendList(queryParam);
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}
	
	@ApiOperation(value = "金融机构推荐列表-热门推荐")
	@GetMapping("/free/hotOrganizationRecommendList")
	public ResponseEntity<ListResult<RecommendOrganizationVO>> hotOrganizationRecommendList(
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		QueryOrganizationRecommend queryParam = new QueryOrganizationRecommend();
		queryParam.setSortField("2");
		queryParam.setPageNum(pageNum);
		queryParam.setPageSize(pageSize);
		log.info("获取热门机构queryParam："+queryParam.toString());
		ListResult<RecommendOrganizationVO> page = productService.queryHotOrganizationRecommendList(queryParam);
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}
	
	@ApiOperation(value = "首页-金融机构推荐列表")
	@GetMapping("/free/organizationRecommendList")
	public ResponseEntity<ListResult<RecommendOrganizationVO>> organizationRecommendList(
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		QueryOrganizationRecommend queryParam = new QueryOrganizationRecommend();
		//首页按服务次数倒叙排列
		queryParam.setSortField("2");
		queryParam.setPageNum(pageNum);
		queryParam.setPageSize(pageSize);
		log.info("获取入驻机构queryParam："+queryParam.toString());
		ListResult<RecommendOrganizationVO> page = productService.queryOrganizationRecommendList(queryParam);
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}
	
	
	@ApiOperation(value = "首页-金融机构详情")
	@GetMapping("/free/getOrganizationRecommend")
	public ResponseEntity<RecommendOrganizationVO> getOrganizationRecommend(@ApiParam(name = "organizationId", value = "所属机构id", required = true) @RequestParam(required = true) Long organizationId) {
		RecommendOrganizationVO info = productService.getOrganizationRecommend(organizationId);
		return ResponseEntity.status(HttpStatus.OK).body(info);
	}
	
	@ApiOperation(value = "我申请过的机构：organizationId为空则不存在申请过的机构")
	@GetMapping("/applyOrganizationHis")
	public ResponseEntity<RecommendOrganizationVO> applyOrganizationHis() {
		QueryOrganizationRecommend queryParam = new QueryOrganizationRecommend();
		RecommendOrganizationVO vo = productService.applyOrganizationHis(queryParam);
		return ResponseEntity.status(HttpStatus.OK).body(vo);
	}
	
	@ApiOperation(value = "提交意见反馈")
	@PostMapping("/addFeedBack")
	public ResponseEntity<String> addFeedBack(@RequestBody @Validated AddFeedBackVO feedBack) {
		CurrentUser currentUser = UserContext.currentUser();
		productService.addFeedBack(currentUser, feedBack);
		log.info(String.format("用户：%s 提交了意见反馈：%s", currentUser.getLoginName(), feedBack.getTitle()));
		return ResponseEntity.status(HttpStatus.OK).body("反馈成功，期待再次为您服务");
	}
	
	@ApiOperation(value = "查询意见反馈列表")
	@GetMapping("/free/queryFeedBackList")
	public ResponseEntity<ListResult<FeedBackVO>> queryFeedBackList(
			@ApiParam(name = "title", value = "标题", required = false) @RequestParam(required = false) String title,
			@ApiParam(name = "type", value = "类型", required = false) @RequestParam(required = false) String type,
			@ApiParam(name = "content", value = "内容", required = false) @RequestParam(required = false) String content,
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		QueryFeedBack queryParam = new QueryFeedBack();
		queryParam.setTitle(title);
		queryParam.setType(type);
		queryParam.setContent(content);
		queryParam.setPageNum(pageNum);
		queryParam.setPageSize(pageSize);
		ListResult<FeedBackVO> page = productService.queryFeedBackList(queryParam);
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}
	
	@ApiOperation(value = "意见反馈详情")
	@GetMapping("/queryFeedBackById")
	public ResponseEntity<FeedBackVO> queryFeedBackById(@ApiParam(name = "id", value = "id", required = true) @RequestParam(required = true) Long id) {
		FeedBackVO vo = productService.queryFeedBackById(id);
		return ResponseEntity.status(HttpStatus.OK).body(vo);
	}
	
	@ApiOperation(value = "申请订单时更新产品表被申请量")
	@PostMapping("/updateProductApplyCount")
	public ResponseEntity<Object> updateProductApplyCount( @RequestBody @Validated ProductApplyCountDTO dto) {
		log.info("产品被申请：{}", dto);
		productService.updateProductApplyCount(dto);
		return ResponseEntity.status(HttpStatus.OK).body(new JSONObject().put("msg", "更新成功"));
	}
	
	@ApiOperation(value = "更新产品被申请金额")
	@PostMapping("/free/updateProductApplyAmount")
	public ResponseEntity<Object> updateProductApplyAmount( @RequestBody @Validated ProductApplyCountDTO dto) {
		log.info("产品被申请金额：{}", dto);
		productService.updateProductApplyAmount(dto);
		return ResponseEntity.status(HttpStatus.OK).body(new JSONObject().put("msg", "更新成功"));
	}
	@ApiOperation(value = "更新产品机构类型")
	@PostMapping("/free/updateFinanceProductOrganizationType")
	public ResponseEntity<Object> updateFinanceProductOrganizationType( @RequestBody @Validated ProductOrganizationTypeDTO dto) {
		log.info("金融产品机构类型：{}", dto);
		productService.updateFinanceProductOrganizationType(dto);
		return ResponseEntity.status(HttpStatus.OK).body(new JSONObject().put("msg", "更新成功"));
	}	
	
	@ApiOperation(value = "产品状态:1上架0下架3删除4不存在")
	@GetMapping("/free/getProductStatus")
	public ResponseEntity<Integer> getProductStatus(
			@ApiParam(name = "id", value = "id", required = true) @RequestParam(required = true) Long id,
			@ApiParam(name = "type", value = "订单详情otype的值：1特色产品2金融产品", required = true) @RequestParam(required = true) String type
			) {
		Integer status = productService.getProductStatus(id,type);
		return ResponseEntity.status(HttpStatus.OK).body(status);
	}
	
	@ApiOperation(value = "获取所有发布产品")
	@GetMapping("/free/getAllProduct")
	public ResponseEntity<List<ProductCommonVO>> getAllProduct() {
		List<ProductCommonVO> list = productService.getAllProduct();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@ApiOperation(value = "获取机构id获取产品相关信息-内部")
	@GetMapping("/free/getOrgProductInfoByOrgId")
	public ResponseEntity<Map<String,Object>> getOrgProductInfoByOrgId(
			@ApiParam  @RequestParam Long id
			) {
		log.info("机构产品最低利率id："+id);
		Map<String,Object> map = productService.getOrgProductInfo(id);
		return ResponseEntity.status(HttpStatus.OK).body(map);
	}
	
	@ApiOperation(value = "金融产品列表筛选条件")
	@GetMapping("/free/financeProductScreeningConditionsList")
	public ResponseEntity<List<ScreeningConditionsVO>> financeProductScreeningConditionsList() {
		List<ScreeningConditionsVO> list = productService.financeProductScreeningConditionsList("2");
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
}
