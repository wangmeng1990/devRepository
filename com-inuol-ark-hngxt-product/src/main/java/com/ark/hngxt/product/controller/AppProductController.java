package com.ark.hngxt.product.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ark.dependencies.common.auth.model.CurrentUser;
import com.ark.dependencies.common.auth.model.ListResult;
import com.ark.dependencies.common.auth.util.UserContext;
import com.ark.dependencies.common.exception.RestApiException;
import com.ark.hngxt.product.model.AddFeedBackAppVO;
import com.ark.hngxt.product.model.CreditProductVO;
import com.ark.hngxt.product.model.FinanceProductVO;
import com.ark.hngxt.product.model.QueryCreditProduct;
import com.ark.hngxt.product.model.QueryFinanceProduct;
import com.ark.hngxt.product.model.QueryOrganizationRecommend;
import com.ark.hngxt.product.model.RecommendOrganizationVO;
import com.ark.hngxt.product.model.ScreeningConditions;
import com.ark.hngxt.product.model.ScreeningConditionsItem;
import com.ark.hngxt.product.model.ScreeningConditionsVO;
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
@Slf4j
@Api(tags = "app产品")
@RestController
@RequestMapping("/")
public class AppProductController {
	@Autowired
	private ProductService productService;

	/**app暂时没做排序  5-8 14:33
	 * */
	@ApiOperation(value = "首页-入住机构")
	@GetMapping("/free/app/organizationRecommendList")
	public ResponseEntity<ListResult<RecommendOrganizationVO>> organizationRecommendList(
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		QueryOrganizationRecommend queryParam = new QueryOrganizationRecommend();
		queryParam.setSortField("2");
		queryParam.setPageNum(pageNum);
		queryParam.setPageSize(pageSize);
		log.info("获取入驻机构queryParam："+queryParam.toString());
		ListResult<RecommendOrganizationVO> page = productService.queryOrganizationRecommendList(queryParam);
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}
	
	@ApiOperation(value = "金融机构详情：机构信息与统计字段")
	@GetMapping("/free/app/getOrganizationRecommend")
	public ResponseEntity<RecommendOrganizationVO> getOrganizationRecommend(@ApiParam(name = "organizationId", value = "所属机构id", required = true) @RequestParam(required = true) Long organizationId) {
		RecommendOrganizationVO info = productService.getOrganizationRecommend(organizationId);
		return ResponseEntity.status(HttpStatus.OK).body(info);
	}
	
	@ApiOperation(value = "我申请过的机构：organizationId为空则不存在申请过的机构")
	@GetMapping("/app/applyOrganizationHis")
	public ResponseEntity<RecommendOrganizationVO> applyOrganizationHis() {
		QueryOrganizationRecommend queryParam = new QueryOrganizationRecommend();
		RecommendOrganizationVO vo = productService.applyOrganizationHis(queryParam);
		return ResponseEntity.status(HttpStatus.OK).body(vo);
	}
	
	@ApiOperation(value = "首页-我要贷款-特色产品推荐")
	@GetMapping("/free/app/queryHotCreditProductList")
	public ResponseEntity<ListResult<CreditProductVO>> queryFirstLoanCreditProductList(
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		QueryCreditProduct queryParam = new QueryCreditProduct();
		queryParam.setStatus(1);
		queryParam.setBeMainHot(1);
		queryParam.setOrderByClause(" interest_rate_start asc ");
		queryParam.setPageNum(pageNum);
		queryParam.setPageSize(pageSize);
		ListResult<CreditProductVO> page = productService.queryCreditProductList(queryParam);
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}
	
	@ApiOperation(value = "根据id查询特色产品详情")
	@GetMapping("/free/app/queryCreditProductById/{id}")
	public ResponseEntity<CreditProductVO> queryCreditProductById(@PathVariable(name = "id") Long id) {
		if (id == null || id < 1) {
			throw new RestApiException("无此产品");
		}
		CreditProductVO product = productService.queryCreditProductById(id);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}
	
	@ApiOperation(value = "根据id查询金融产品详情")
	@GetMapping("/free/app/queryFinanceProductById/{id}")
	public ResponseEntity<FinanceProductVO> queryFinanceProductById(@PathVariable(name = "id") Long id) {
		if (id == null || id < 1) {
			throw new RestApiException("无此产品");
		}
		FinanceProductVO product = productService.queryFinanceProductById(id);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}
	
	@ApiOperation(value = "机构推荐列表-机构详情-查询特色产品")
	@GetMapping("/free/app/queryOrganizationDetailCreditProductList")
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
	@GetMapping("/free/app/queryOrganizationDetailFinanceProductList")
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
	
	@ApiOperation(value = "产品-特色产品列表")
	@GetMapping("/free/app/queryOrganizationCreditProductList")
	public ResponseEntity<ListResult<CreditProductVO>> queryOrganizationCreditProductList(
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		QueryCreditProduct queryParam = new QueryCreditProduct();
		queryParam.setStatus(1);
		queryParam.setOrderByClause(" sort asc, interest_rate_start asc ");
		queryParam.setPageNum(pageNum);
		queryParam.setPageSize(pageSize);
		ListResult<CreditProductVO> page = productService.queryCreditProductList(queryParam);
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}
	
	@ApiOperation(value = "产品-金融产品列表",notes = "筛选条件对应的value传给后台")
	@GetMapping("/free/app/queryFinanceProductMarket")
	public ResponseEntity<ListResult<FinanceProductVO>> queryFinanceProductMarket(
			@ApiParam(name = "name", value = "产品名称", required = false) @RequestParam(required = false) String name,
			@ApiParam(name = "screeningConditions", value = "筛选条件：根据筛选条件接口返回构造的jsonString,不选条件传空字符串", required = false) @RequestParam(required = false) String screeningConditions,
			@ApiParam(name = "sort", value = "jsonArray字符串：[{field:排序字段（1申请量2周期3利率）,sort:1升序2降序},{}]，综合不传或空数组", required = false) @RequestParam(required = false) String sort,
			@ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@ApiParam(name = "pageSize", value = "每页数据条数", required = false) @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		log.info("app金融产品查询sort:"+sort+";screeningConditions:"+screeningConditions);
		if(DataUtils.isNotEmpty(sort)) {
			try {
				sort=URLDecoder.decode(sort, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				log.error("app金融产品超市sort转码异常:"+e.getMessage());
			}
		}
		log.info("app金融产品超市sort:"+sort);
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
	
	@ApiOperation(value = "机构-我的-信用产品")
	@GetMapping("/app/queryOrganizationCreditProductList")
	public ResponseEntity<ListResult<CreditProductVO>> queryMyOrganizationCreditProductList(
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
		
	@ApiOperation(value = "机构-我的-金融产品")
	@GetMapping("/app/queryMyOrganizationFinanceProductList")
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
	
	@ApiOperation(value = "金融产品列表筛选条件")
	@GetMapping("/free/app/financeProductScreeningConditionsList")
	public ResponseEntity<List<ScreeningConditionsVO>> financeProductScreeningConditionsList() {
		List<ScreeningConditionsVO> list = productService.financeProductScreeningConditionsList("1");
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@ApiOperation(value = "提交意见反馈")
	@PostMapping("/app/addFeedBack")
	public ResponseEntity<String> addFeedBack(@RequestBody @Validated AddFeedBackAppVO feedBack) {
		log.info("app提交意见反馈{}",feedBack);
		CurrentUser currentUser = UserContext.currentUser();
		productService.addFeedBackApp(currentUser, feedBack);
		log.info(String.format("用户：%s 提交了意见反馈：%s", currentUser.getLoginName(), feedBack.getType()));
		return ResponseEntity.status(HttpStatus.OK).body("反馈成功，期待再次为您服务");
	}
	
	@ApiOperation(value = "意见反馈-针对对象参数字典")
	@GetMapping("/free/app/feedBackDictList")
	public ResponseEntity<List<ScreeningConditionsItem>> feedBackDictList(
			@ApiParam(name = "code", value = "字典code：针对对象：feed_back_0", required = false) @RequestParam(required = false) String code
			) {
		List<ScreeningConditionsItem> list = productService.feedBackDictList(code);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
}
