package com.ark.hngxt.product.service.ipml;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ark.dependencies.common.auth.model.CurrentUser;
import com.ark.dependencies.common.auth.model.ListResult;
import com.ark.dependencies.common.exception.RestApiException;
import com.ark.dependencies.common.mq.MqProducer;
import com.ark.hngxt.product.domain.CreditProduct;
import com.ark.hngxt.product.domain.CreditProductCriteria;
import com.ark.hngxt.product.domain.CreditProductCriteria.Criteria;
import com.ark.hngxt.product.domain.CreditProductWithBLOBs;
import com.ark.hngxt.product.domain.FeedBack;
import com.ark.hngxt.product.domain.FeedBackCriteria;
import com.ark.hngxt.product.domain.FinanceProduct;
import com.ark.hngxt.product.domain.FinanceProductCriteria;
import com.ark.hngxt.product.domain.FinanceProductWithBLOBs;
import com.ark.hngxt.product.domain.ProductBigData;
import com.ark.hngxt.product.mapper.CreditProductMapper;
import com.ark.hngxt.product.mapper.FeedBackMapper;
import com.ark.hngxt.product.mapper.FinanceProductMapper;
import com.ark.hngxt.product.mapper.ProductBigDataMapper;
import com.ark.hngxt.product.mapper.RecommendOrganizationMapper;
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
import com.ark.hngxt.product.model.ProductItemVO;
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
import com.ark.hngxt.product.model.SortVO;
import com.ark.hngxt.product.model.UpdateCreditProductVO;
import com.ark.hngxt.product.model.UpdateFinanceProductVO;
import com.ark.hngxt.product.model.UpdateStrategySetVO;
import com.ark.hngxt.product.model.intelligent.match.ProductToBigData;
import com.ark.hngxt.product.model.statistics.Product;
import com.ark.hngxt.product.service.ProductService;
import com.ark.hngxt.product.util.BeanCopierUtils;
import com.ark.hngxt.product.util.DataUtils;
import com.ark.hngxt.product.util.DateUtils;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	private FinanceProductMapper financeProductMapper;
	@Autowired
	private CreditProductMapper creditProductMapper;
	@Autowired
	private RecommendOrganizationMapper recommendOrganizationMapper;
	@Autowired
	private FeedBackMapper feedBackMapper;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
    MqProducer mqProducerService;
	@Autowired
	private ProductBigDataMapper productBigDataMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class,readOnly = false)
	public Long addFinanceProduct(CurrentUser currentUser, AddFinanceProductVO product) {
		checkFinanceProduct(product.getName(),null);
		if(null==product.getBeFace()||product.getBeFace().intValue()!=1) {
			if(null==product.getInterestRateStart()||null==product.getInterestRateEnd()) {
				throw new RestApiException("利率不能为空");
			}
		}
		FinanceProductWithBLOBs record=new FinanceProductWithBLOBs();
		BeanCopierUtils.copy(product, record);
		record.setCreateBy(String.valueOf(currentUser.getUserId()));
		record.setStatus(0);
		//设置机构类型
		JSONObject parentOrganization=getOrganization(record.getParentOrganizationId());
		if(null!=parentOrganization) {
			record.setOrganizationType(null!=parentOrganization?parentOrganization.getString("category"):"");
		}
		financeProductMapper.insertSelective(record);
		//同步数据到统计服务
		Product p=new Product();
		if(null!=record) {
			BeanCopierUtils.copy(record, p);
			p.setProductId(record.getId());
			p.setProductName(record.getName());
			p.setType("2");
			p.setFinanceType(record.getType());
			p.setPCreateBy(record.getCreateBy());
			p.setPCreateDate(record.getCreateDate());
			p.setStatus(0);
			productStatistic(p);
		}
		return record.getId();
	}
	
	private void checkFinanceProduct(String productName,Long id) {
		FinanceProductCriteria example=new FinanceProductCriteria();
		com.ark.hngxt.product.domain.FinanceProductCriteria.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(productName);
		if(null!=id) {
			criteria.andIdNotEqualTo(id);
		}
		financeProductMapper.countByExample(example);
		if(financeProductMapper.countByExample(example)>0) {
			throw new RestApiException("产品名称已存在");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class,readOnly = false)
	public Long addCreditProduct(CurrentUser currentUser, AddCreditProductVO product) {
		checkCreditProduct(product.getName(),null);
		CreditProductWithBLOBs record=new CreditProductWithBLOBs();
		BeanCopierUtils.copy(product, record);
		record.setCreateBy(String.valueOf(currentUser.getUserId()));
		record.setStatus(0);
		creditProductMapper.insertSelective(record);
		//同步数据到统计服务
		Product p=new Product();
		if(null!=record) {
			BeanCopierUtils.copy(record, p);
			p.setProductId(record.getId());
			p.setProductName(record.getName());
			p.setType("1");
			p.setFinanceType("特色产品");
			p.setPCreateBy(record.getCreateBy());
			p.setPCreateDate(record.getCreateDate());
			p.setStatus(0);
			productStatistic(p);
		}
		return record.getId();
	}


	private void checkCreditProduct(String productName, Long id) {
		CreditProductCriteria example=new CreditProductCriteria();
		com.ark.hngxt.product.domain.CreditProductCriteria.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(productName);
		if(null!=id) {
			criteria.andIdNotEqualTo(id);
		}
		creditProductMapper.countByExample(example);
		if(creditProductMapper.countByExample(example)>0) {
			throw new RestApiException("产品名称已存在");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class,readOnly = false)
	public void updateFinanceProduct(CurrentUser currentUser, UpdateFinanceProductVO updateProduct) {
		checkFinanceProduct(updateProduct.getName(),updateProduct.getId());
		if(null==updateProduct.getBeFace()||updateProduct.getBeFace().intValue()!=1) {
			if(null==updateProduct.getInterestRateStart()||null==updateProduct.getInterestRateEnd()) {
				throw new RestApiException("利率不能为空");
			}
		}
		FinanceProductWithBLOBs record = new FinanceProductWithBLOBs();
		BeanCopierUtils.copy(updateProduct, record);
		record.setUpdateBy(String.valueOf(currentUser.getUserId()));
		//设置机构类型
		JSONObject parentOrganization=getOrganization(record.getParentOrganizationId());
		if(null!=parentOrganization) {
			record.setOrganizationType(null!=parentOrganization?parentOrganization.getString("category"):"");
		}
		financeProductMapper.updateByPrimaryKeySelective(record);
		//同步数据到统计服务
		Product p=new Product();
		if(null!=record) {
			BeanCopierUtils.copy(record, p);
			p.setProductId(record.getId());
			p.setProductName(record.getName());
			p.setType("2");
			p.setFinanceType(record.getType());
			p.setPCreateBy(record.getCreateBy());
			p.setPCreateDate(record.getCreateDate());
			productStatistic(p);
		}
		//推送大数据产品信息
		FinanceProductWithBLOBs bean = financeProductMapper.selectByPrimaryKey(updateProduct.getId());
		if(null!=bean.getStatus()&&bean.getStatus().intValue()==1) {
			ProductToBigData productBigData =new ProductToBigData();
			BeanCopierUtils.copy(bean, productBigData);
			productBigData.setFinanceType(bean.getType());
			productBigData.setProductType("2");
			productBigData.setProductId(bean.getId());
			pushProduct(productBigData);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class,readOnly = false)
	public void updateCreditProduct(CurrentUser currentUser, UpdateCreditProductVO updateProduct) {
		checkCreditProduct(updateProduct.getName(),updateProduct.getId());
		CreditProductWithBLOBs record=new CreditProductWithBLOBs();
		BeanCopierUtils.copy(updateProduct, record);
		record.setUpdateBy(String.valueOf(currentUser.getUserId()));
		creditProductMapper.updateByPrimaryKeySelective(record);
		//同步数据到统计服务
		Product p=new Product();
		if(null!=record) {
			BeanCopierUtils.copy(record, p);
			p.setProductId(record.getId());
			p.setProductName(record.getName());
			p.setType("1");
			p.setFinanceType("特色产品");
			p.setPCreateBy(record.getCreateBy());
			p.setPCreateDate(record.getCreateDate());
			productStatistic(p);
		}
		// 推送大数据产品信息
		CreditProductWithBLOBs bean = creditProductMapper.selectByPrimaryKey(updateProduct.getId());
		if (null != bean.getStatus() && bean.getStatus().intValue() == 1) {
			ProductToBigData productBigData = new ProductToBigData();
			BeanCopierUtils.copy(bean, productBigData);
			productBigData.setFinanceType("特色产品");
			productBigData.setProductType("1");
			productBigData.setProductId(bean.getId());
			pushProduct(productBigData);
		}
	}


	@Override
	public FinanceProductVO queryFinanceProductById(Long id) {
		FinanceProductWithBLOBs bean = financeProductMapper.selectByPrimaryKey(id);
		FinanceProductVO vo=new FinanceProductVO();
		if(null!=bean) {
			BeanCopierUtils.copy(bean, vo);
			vo=getFinanceProductVo(vo);
		}
		return vo;
	}

	@Override
	public CreditProductVO queryCreditProductById(Long id) {
		CreditProductWithBLOBs bean = creditProductMapper.selectByPrimaryKey(id);
		CreditProductVO vo=new CreditProductVO();
		if(bean!=null) {
			BeanCopierUtils.copy(bean, vo);
			vo=getCreditProductVo(vo,null);
		}
		return vo;
	}
	

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public void updateFinanceProductStatus(CurrentUser currentUser, Long id, int status) {
		FinanceProductWithBLOBs record = new FinanceProductWithBLOBs();
		record.setId(id);
		record.setStatus(status);
		record.setUpdateBy(String.valueOf(currentUser.getUserId()));
		//发布之后信息同步到同步服务
		Product p=new Product();
		FinanceProductWithBLOBs bean=new FinanceProductWithBLOBs();
		if (status == 1) {
			 bean = financeProductMapper.selectByPrimaryKey(id);
			// 设置首次发布时间
			if (null == bean.getPublishDate()) {
				Date publishDate = new Date();
				record.setPublishDate(publishDate);
				bean.setPublishDate(publishDate);
			}
			//推送大数据产品信息
			ProductToBigData productBigData =new ProductToBigData();
			BeanCopierUtils.copy(bean, productBigData);
			productBigData.setFinanceType(bean.getType());
			productBigData.setProductType("2");
			productBigData.setProductId(bean.getId());
			productBigData.setStatus(status);
			pushProduct(productBigData);
		}else if(status==3) {
			 bean = financeProductMapper.selectByPrimaryKey(id);
			if(bean.getStatus()!=0) {
				throw new RestApiException("只能删除下架的产品");
			}
		}
		financeProductMapper.updateByPrimaryKeySelective(record);
		if(null!=bean) {
			BeanCopierUtils.copy(bean, p);
			p.setProductId(id);
			p.setProductName(bean.getName());
			p.setType("2");
			p.setFinanceType(bean.getType());
			p.setPCreateBy(bean.getCreateBy());
			p.setPCreateDate(bean.getCreateDate());
			p.setStatus(status);
			productStatistic(p);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public void updateCreditProductStatus(CurrentUser currentUser, Long id, int status) {
		CreditProductWithBLOBs record = new CreditProductWithBLOBs();
		record.setId(id);
		record.setStatus(status);
		record.setUpdateBy(String.valueOf(currentUser.getUserId()));
		//发布之后信息同步到同步服务
		Product p=new Product();
		// 设置首次发布时间
		CreditProductWithBLOBs bean=new CreditProductWithBLOBs();
		if (status == 1) {
			 bean = creditProductMapper.selectByPrimaryKey(id);
			if (null == bean.getPublishDate()) {
				Date publishDate = new Date();
				record.setPublishDate(publishDate);
				bean.setPublishDate(publishDate);
			}
			//推送大数据产品信息
			ProductToBigData productBigData =new ProductToBigData();
			BeanCopierUtils.copy(bean, productBigData);
			productBigData.setFinanceType("特色产品");
			productBigData.setProductType("1");
			productBigData.setProductId(bean.getId());
			productBigData.setStatus(status);
			pushProduct(productBigData);
		}else if(status==3) {
			 bean = creditProductMapper.selectByPrimaryKey(id);
			if(bean.getStatus()!=0) {
				throw new RestApiException("只能删除下架的产品");
			}
		}
		creditProductMapper.updateByPrimaryKeySelective(record);
		
		if(null!=bean) {
			BeanCopierUtils.copy(bean, p);
			p.setProductId(id);
			p.setProductName(bean.getName());
			p.setType("1");
			p.setFinanceType("特色产品");
			p.setPCreateBy(bean.getCreateBy());
			p.setPCreateDate(bean.getCreateDate());
			p.setStatus(status);
			productStatistic(p);
		}
	}

	private List<FinanceProductVO> getFinanceProductVoList(List<FinanceProductWithBLOBs> list) {
		List<FinanceProductVO> voList = new ArrayList<FinanceProductVO>();
		for (FinanceProductWithBLOBs bean : list) {
			FinanceProductVO target = new FinanceProductVO();
			BeanCopierUtils.copy(bean, target);
			target = getFinanceProductVo(target);
			voList.add(target);
		}
		return voList;
	}

	private FinanceProductVO getFinanceProductVo(FinanceProductVO target) {
		if(null!=target.getAmountStart()&&null!=target.getAmountEnd()) {
			target.setAmount(target.getAmountStart()+"-"+target.getAmountEnd());
			target.setAmountUnit("万");	
		}
		if(null!=target.getInterestRateStart()) {
			target.setInterestRate(target.getInterestRateStart()+"%-"+target.getInterestRateEnd()+"%");	
		}
		if(null!=target.getLimitStart()&&null!=target.getLimitEnd()) {
			target.setLimit(target.getLimitStart()+"-"+target.getLimitEnd());
			target.setLimitUnit("月");	
		}
		if(DataUtils.isNotEmpty(target.getApplyCondition())) {
			target.setApplyConditionList(getList(target.getApplyCondition(),";"));
		}
		if(DataUtils.isNotEmpty(target.getApplyMaterial())) {
			target.setApplyMaterialList(getList(target.getApplyMaterial(),";"));
		}
		if(DataUtils.isNotEmpty(target.getFeature())) {
			target.setFeatureList(getList(target.getFeature(),";"));
		}
		if(DataUtils.isNotEmpty(target.getTag())) {
			target.setTagList(getList(target.getTag(),","));
		}
		target.setOrderType("2");
		target.setStatusStr(null!=target.getStatus()&&target.getStatus().intValue()==1?"启用":"关闭");
		
		JSONObject organization=getOrganization(target.getOrganizationId());
		JSONObject parentOrganization=getOrganization(target.getParentOrganizationId());
		target.setOrganization(null!=organization?organization.getString("institutionName"):"");
		target.setParentOrganization(null!=parentOrganization?parentOrganization.getString("institutionName"):"");
		target.setOrganizationLogo(null!=parentOrganization?parentOrganization.getString("logoFile"):"");
		target.setLogo(null!=parentOrganization?parentOrganization.getString("circleLogoFile"):"");
		return target;
	}

	private JSONObject getOrganization(Long organizationId) {
		try {
			if (null != organizationId) {
				String url = "http://com-ark-basic-uaa/free/institutions/getById?id=" + organizationId;
				ResponseEntity<JSONObject> entity = restTemplate.getForEntity(url, JSONObject.class);
				if (entity.getStatusCode().is2xxSuccessful()) {
					JSONObject res = entity.getBody();
					log.info("获取机构信息-success:" + res);
					return res;
				} else {
					log.error("获取机构信息-error:" + entity.getBody());
					return null;
				}
			} else {
				log.info("获取机构信息：organizationId为空");
				return null;
			}
		} catch (Exception e) {
			log.error("获取机构信息异常："+e);
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	private Map getOrganizations(List<Long>  organizationIds) {
		try {
			if (DataUtils.isNotEmpty(organizationIds)) {
				String url = "http://com-ark-basic-uaa/free/institutions/getByIds";
				ResponseEntity<Map> entity = restTemplate.postForEntity(url, organizationIds, Map.class);
				if (entity.getStatusCode().is2xxSuccessful()) {
					Map res = entity.getBody();
					log.info("获取机构信息列表-success:" + res);
					return res;
				} else {
					log.error("获取机构信息列表-error:" + entity.getBody());
					return null;
				}
			} else {
				log.info("获取机构信息列表：organizationIds为空");
				return null;
			}
		} catch (Exception e) {
			log.error("获取机构信息列表异常："+e);
			return null;
		}
	}
	
	private String productStatistic(Product product) {
		log.info("产品统计信息开始同步：" + product.toString());
		String url = "http://com-ark-hngxt-statistic/free/productStatistic";
		ResponseEntity<String> entity = restTemplate.postForEntity(url, product, String.class);
		if (entity.getStatusCode().is2xxSuccessful()) {
			String res = entity.getBody();
			log.info("同步产品统计信息-success:" + res);
			return res;
		} else {
			log.error("同步产品统计信息-error:" + entity.getBody());
			return null;
		}
	}
	
	
	private List<String> getList(String param,String split) {
		List<String> list=new ArrayList<String>();
		if(DataUtils.isNotEmpty(param)) {
			String[] arr = param.split(split);
			list=Arrays.asList(arr);
		}
		return list;
	}

	@Override
	public ListResult<CreditProductVO> queryCreditProductList(QueryCreditProduct queryParam) {
		CreditProductCriteria example=new CreditProductCriteria();
		com.ark.hngxt.product.domain.CreditProductCriteria.Criteria criteria = example.createCriteria();
		criteria.andStatusNotEqualTo(3);
		if(DataUtils.isNotEmpty(queryParam.getName())) {
			criteria.andNameLike("%"+queryParam.getName()+"%");
		}
		if(null!=queryParam.getStatus()) {
			criteria.andStatusEqualTo(queryParam.getStatus());
		}
		if(null!=queryParam.getOrganizationId()) {
			criteria.andOrganizationIdEqualTo(queryParam.getOrganizationId());
		}
		if(null!=queryParam.getParentOrganizationId()) {
			criteria.andParentOrganizationIdEqualTo(queryParam.getParentOrganizationId());
		}
		if(null!=queryParam.getBeHot()) {
			criteria.andBeHotEqualTo(queryParam.getBeHot());
		}
		if(null!=queryParam.getBeMainHot()) {
			criteria.andBeMainHotEqualTo(queryParam.getBeMainHot());
		}
		if(null!=queryParam.getBeMainHotApp()) {
			criteria.andBeMainHotAppEqualTo(queryParam.getBeMainHotApp());
		}
		if(DataUtils.isNotEmpty(queryParam.getOrderByClause())) {
			example.setOrderByClause(queryParam.getOrderByClause());
		}else {
			example.setOrderByClause(" create_date desc");
		}
		PageHelper.startPage(queryParam.getPageNum(), queryParam.getPageSize());
		List<CreditProductWithBLOBs> list = creditProductMapper.selectByExampleWithBLOBs(example);
		PageInfo<CreditProductWithBLOBs> pageInfo = new PageInfo<CreditProductWithBLOBs>(list);
		ListResult<CreditProductVO> listResult = new ListResult<CreditProductVO>();
		listResult.setTotalElements(pageInfo.getTotal());
		listResult.setContent(getCreditProductVoList(list,queryParam.getGetRateAndAmount()));
		return listResult;
	}

//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	private Map<String, Object> getCountMap(List<Long> idList,Integer type) throws Exception{
//		String productType="";
//		if(1==type) {
//			productType="特色产品";
//		}else if(2==type) {
//			productType="金融产品";
//		}
//		Map<String, Object> countMap = null;
//		String url = "http://com-ark-hngxt-order/firstOrder/free/selectFirstOrderRegCount";
//		JSONObject ob = new JSONObject();
//		ob.put("otype", type);
//		ob.put("pidArr", idList);
//		log.info("查询"+productType+"被申请数param：" + ob.toString());
//		HttpHeaders requestHeaders = new HttpHeaders();
//	    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
//	    HttpEntity<String> requestEntity = new HttpEntity<String>(ob.toString(), requestHeaders);
//		ResponseEntity<Map> entity = restTemplate.postForEntity(url, requestEntity, Map.class);
//		if (entity.getStatusCode().is2xxSuccessful()) {
//			countMap= entity.getBody();
//			log.info("查询"+productType+"被申请数return-countMap:" + countMap.toString());
//		}else {
//			log.error("查询"+productType+"被申请数error-countMap：" + entity.getBody());
//		}
//		return countMap;
//	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String, Object> getRateAndAmountMap(List<Long> idList,Integer type) throws Exception{
		String productType="";
		if(1==type) {
			productType="特色产品";
		}else if(2==type) {
			productType="金融产品";
		}
		Map<String, Object> countMap = null;
		String url = "http://com-ark-hngxt-statistic/order/free/productOrderSummary";
		JSONObject ob = new JSONObject();
		ob.put("otype", type);
		ob.put("pidArr", idList);
		log.info("查询"+productType+"申请成功率和累计放款额param：" + ob);
		HttpHeaders requestHeaders = new HttpHeaders();
	    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<JSONObject> requestEntity = new HttpEntity<JSONObject>(ob, requestHeaders);
		ResponseEntity<Map> entity = restTemplate.postForEntity(url, requestEntity, Map.class);
		if (entity.getStatusCode().is2xxSuccessful()) {
			countMap= entity.getBody();
			log.info("查询"+productType+"申请成功率和累计放款额return-countMap:" + countMap);
		}else {
			log.error("查询"+productType+"申请成功率和累计放款额error-countMap：" + entity.getBody());
		}
		return countMap;
	}
	
	/**
	 * @param list
	 * @param getRateAndAmount 
	 * @param applyCount:是否查询该产品有多少家企业申请
	 * @return
	 */
	private List<CreditProductVO> getCreditProductVoList(List<CreditProductWithBLOBs> list, Boolean getRateAndAmount) {
		List<CreditProductVO> voList = new ArrayList<CreditProductVO>();
		Map<String, Object> rateAndAmountMap = null;
		if (getRateAndAmount && DataUtils.isNotEmpty(list)) {
			try {
				List<Long> idList = list.stream().map(CreditProductWithBLOBs::getId).distinct()
						.collect(Collectors.toList());
				rateAndAmountMap = getRateAndAmountMap(idList, 1);
			} catch (Exception e) {
				log.error("查询信用产品申请成功率和累计放款额error：" + e);
			}
		}
		for (CreditProductWithBLOBs bean : list) {
			CreditProductVO target = new CreditProductVO();
			BeanCopierUtils.copy(bean, target);
			target = getCreditProductVo(target,rateAndAmountMap);
			voList.add(target);
		}
		return voList;
	}

	@SuppressWarnings("rawtypes")
	private CreditProductVO getCreditProductVo(CreditProductVO target, Map<String, Object> rateAndAmountMap) {
		if(null!=target.getAmountStart()&&null!=target.getAmountEnd()) {
			target.setAmount(target.getAmountStart()+"-"+target.getAmountEnd());
			target.setAmountUnit("万");	
		}
		if(null!=target.getInterestRateStart()) {
			target.setInterestRate(target.getInterestRateStart()+"%-"+target.getInterestRateEnd()+"%");	
		}
		if(null!=target.getLimitStart()&&null!=target.getLimitEnd()) {
			target.setLimit(target.getLimitStart()+"-"+target.getLimitEnd());
			target.setLimitUnit("月");	
		}
		if(DataUtils.isNotEmpty(target.getApplyCondition())) {
			target.setApplyConditionList(getList(target.getApplyCondition(),";"));
		}
		if(DataUtils.isNotEmpty(target.getApplyMaterial())) {
			target.setApplyMaterialList(getList(target.getApplyMaterial(),";"));
		}
		if(DataUtils.isNotEmpty(target.getFeature())) {
			target.setFeatureList(getList(target.getFeature(),";"));
		}
		target.setOrderType("1");
		target.setStatusStr(null!=target.getStatus()&&target.getStatus().intValue()==1?"启用":"关闭");
		
		JSONObject organization=getOrganization(target.getOrganizationId());
		JSONObject parentOrganization=getOrganization(target.getParentOrganizationId());
		target.setOrganization(null!=organization?organization.getString("institutionName"):"");
		target.setParentOrganization(null!=parentOrganization?parentOrganization.getString("institutionName"):"");
		target.setOrganizationLogo(null!=parentOrganization?parentOrganization.getString("logoFile"):"");
		target.setLogo(null!=parentOrganization?parentOrganization.getString("circleLogoFile"):"");
		target.setApplyRate("0%");
		target.setTotalAmount(new BigDecimal(0));
		if(null!=rateAndAmountMap&&rateAndAmountMap.containsKey(String.valueOf(target.getId()))){
			Map map = (Map)rateAndAmountMap.get(target.getId().toString());
			if(null!=map) {
				BigDecimal b=new BigDecimal((String)map.get("applicationSuccessRate")).setScale(0, BigDecimal.ROUND_HALF_UP);
				String applicationSuccessRate = b+"%";
				target.setApplyRate(applicationSuccessRate);
				target.setTotalAmount(new BigDecimal((Double)map.get("money")).setScale(0, BigDecimal.ROUND_HALF_UP));
			}
		}
		return target;
	}

	@Override
	public ListResult<FinanceProductVO> queryFinanceProductList(QueryFinanceProduct queryParam) {
		FinanceProductCriteria example = new FinanceProductCriteria();
		com.ark.hngxt.product.domain.FinanceProductCriteria.Criteria criteria = example.createCriteria();
		criteria.andStatusNotEqualTo(3);
		
		if(DataUtils.isNotEmpty(queryParam.getName())) {
			criteria.andNameLike("%"+queryParam.getName()+"%");
		}
		if(null!=queryParam.getStatus()) {
			criteria.andStatusEqualTo(queryParam.getStatus());
		}
		if(DataUtils.isNotEmpty(queryParam.getOrganization())) {
			criteria.andOrganizationLike("%"+queryParam.getOrganization()+"%");
		}
		if(null!=queryParam.getOrganizationId()) {
			criteria.andOrganizationIdEqualTo(Long.valueOf(queryParam.getOrganizationId()));
		}
		if(null!=queryParam.getParentOrganizationId()) {
			criteria.andParentOrganizationIdEqualTo(queryParam.getParentOrganizationId());
		}
		if(null!=queryParam.getBeBigDataHot()) {
			criteria.andBeBigDataHotEqualTo(queryParam.getBeBigDataHot());
		}
		
		if(DataUtils.isNotEmpty(queryParam.getCreateDate())) {
			Date value1=DateUtils.stringToDate(queryParam.getCreateDate()+" 00:00:00");
			Date value2=DateUtils.stringToDate(queryParam.getCreateDate()+" 23:59:59");
			criteria.andCreateDateGreaterThanOrEqualTo(value1).andCreateDateLessThan(value2);
		}
		if (DataUtils.isNotEmpty(queryParam.getAmount())) {
			String[] amountArr = queryParam.getAmount().split("-");
			if (amountArr.length == 2) {
				if(DataUtils.isEmpty(amountArr[0])) {
					amountArr[0]="0";
				}
				criteria.andAmountStartLessThan(new BigDecimal(amountArr[1])).andAmountEndGreaterThan(new BigDecimal(amountArr[0]));
			} else {
				criteria.andAmountEndGreaterThan(new BigDecimal(amountArr[0]));
			}
		}
		if(DataUtils.isNotEmpty(queryParam.getLimit())) {
			String[] limitArr = queryParam.getLimit().split("-");
			if (limitArr.length == 2) {
				if(DataUtils.isEmpty(limitArr[0])) {
					limitArr[0]="0";
				}
				criteria.andLimitStartLessThan(Integer.valueOf(limitArr[1])).andLimitEndGreaterThan(Integer.valueOf(limitArr[0]));
			} else {
				criteria.andLimitEndGreaterThan(Integer.valueOf(limitArr[0]));
			}
		}
		if(DataUtils.isNotEmpty(queryParam.getType())) {
			criteria.andTypeEqualTo(queryParam.getType());
		}
		if(DataUtils.isNotEmpty(queryParam.getOrderByClause())) {
			example.setOrderByClause(queryParam.getOrderByClause());
		}else {
			example.setOrderByClause(" create_date desc");
		}
		PageHelper.startPage(queryParam.getPageNum(), queryParam.getPageSize());
		List<FinanceProductWithBLOBs> list = financeProductMapper.selectByExampleWithBLOBs(example);
		PageInfo<FinanceProductWithBLOBs> pageInfo = new PageInfo<FinanceProductWithBLOBs>(list);
		ListResult<FinanceProductVO> listResult = new ListResult<FinanceProductVO>();
		listResult.setTotalElements(pageInfo.getTotal());
		listResult.setContent(getFinanceProductVoList(list));
		return listResult;
	}


	@Override
	public ListResult<FinanceProductVO> queryFinanceProductMarket(QueryFinanceProduct queryParam) {
		Map<String, Object> queryMap=getQueryFinanceParamMap(queryParam);
		PageHelper.startPage(queryParam.getPageNum(), queryParam.getPageSize());
		List<FinanceProductWithBLOBs> list=financeProductMapper.queryFinanceProductMarket(queryMap);
		PageInfo<FinanceProductWithBLOBs> pageInfo = new PageInfo<FinanceProductWithBLOBs>(list);
		ListResult<FinanceProductVO> listResult = new ListResult<FinanceProductVO>();
		listResult.setTotalElements(pageInfo.getTotal());
		listResult.setContent(getFinanceProductVoList(list));
		return listResult;
	}

	private Map<String, Object> getQueryFinanceParamMap(QueryFinanceProduct queryParam) {
		Map<String, Object> queryMap=new HashMap<String, Object>();
		queryMap.put("name", queryParam.getName());
		queryMap.put("orderByClause", queryParam.getOrderByClause());
		if(DataUtils.isNotEmpty(queryParam.getType())) {
			queryMap.put("typeList", Arrays.asList(queryParam.getType().split(",")));
		}
		if(DataUtils.isNotEmpty(queryParam.getApplyArea())) {
			if(!queryParam.getApplyArea().contains("city_0_15")) {//例如选长沙，则湖南省内也会查到
				queryParam.setApplyArea(queryParam.getApplyArea()+",city_0_15");
			}
			queryMap.put("applyAreaList", Arrays.asList(queryParam.getApplyArea().split(",")));
		}
		if(DataUtils.isNotEmpty(queryParam.getGuaranteeType())) {
			queryMap.put("guaranteeTypeList", Arrays.asList(queryParam.getGuaranteeType().split(",")));
		}
		if(DataUtils.isNotEmpty(queryParam.getOrganizationType())) {
			queryMap.put("organizationTypeList", Arrays.asList(queryParam.getOrganizationType().split(",")));
		}
		if(DataUtils.isNotEmpty(queryParam.getOrganizationId())) {
			queryMap.put("organizationIdList", Arrays.asList(queryParam.getOrganizationId().split(",")));
		}
		queryMap.put("amountList", getAmount(queryParam.getAmount()));
		queryMap.put("limitList", getLimit(queryParam.getLimit()));
		
		//综合排序
		List<SortVO> newSortList=getSort(queryParam.getSort());
		queryMap.put("sortList", newSortList);
		return queryMap;
	}

	private List<SortVO> getSort(String sort) {
		List<SortVO> newSortList=new ArrayList<SortVO>();
		if(DataUtils.isNotEmpty(sort)) {
			List<SortVO> sortList=JSONArray.parseArray(sort, SortVO.class);
			if(DataUtils.isNotEmpty(sortList)) {
				for(SortVO vo:sortList) {
					SortVO newVo=new SortVO();
					String thisSort="";
					if("1".equals(vo.getSort())) {
						thisSort=" asc ";
					}else if("2".equals(vo.getSort())) {
						thisSort=" desc ";
					}
					if("1".equals(vo.getField())) {
						newVo.setField(" apply_count ");
						newVo.setSort(thisSort);
						newSortList.add(newVo);
					}else if("3".equals(vo.getField())) {
						SortVO newVo2=new SortVO();
						newVo2.setField(" be_face ");
						//面议放最后
						newVo2.setSort(" asc ");
						newSortList.add(newVo2);
						if("1".equals(vo.getSort())) {
							newVo.setField(" interest_rate_start ");
							newVo.setSort(thisSort);
							newSortList.add(newVo);
							SortVO newVo1=new SortVO();
							newVo1.setField(" interest_rate_end ");
							newVo1.setSort(thisSort);
							newSortList.add(newVo1);
						}else {
							newVo.setField(" interest_rate_end ");
							newVo.setSort(thisSort);
							newSortList.add(newVo);
							SortVO newVo1=new SortVO();
							newVo1.setField(" interest_rate_start ");
							newVo1.setSort(thisSort);
							newSortList.add(newVo1);
						}
					}else if("2".equals(vo.getField())) {
						if("1".equals(vo.getSort())) {
							newVo.setField(" limit_start ");
							newVo.setSort(thisSort);
							newSortList.add(newVo);
							SortVO newVo1=new SortVO();
							newVo1.setField(" limit_end ");
							newVo1.setSort(thisSort);
							newSortList.add(newVo1);
						}else {
							newVo.setField(" limit_end ");
							newVo.setSort(thisSort);
							newSortList.add(newVo);
							SortVO newVo1=new SortVO();
							newVo1.setField(" limit_start ");
							newVo1.setSort(thisSort);
							newSortList.add(newVo1);
						}
					}
				}
			}else {
				newSortList=getAcquiesceSort();
			}
		}else {
			newSortList=getAcquiesceSort();
		}
		return newSortList;
	}

	private List<SortVO> getAcquiesceSort() {
		List<SortVO> list=new ArrayList<SortVO>();
		SortVO newVo3=new SortVO();
		newVo3.setField(" be_face ");
		newVo3.setSort(" asc ");
		
		SortVO newVo1=new SortVO();
		newVo1.setField(" sort ");
		newVo1.setSort(" asc ");
		
		SortVO newVo2=new SortVO();
		newVo2.setField(" publish_date ");
		newVo2.setSort(" desc ");
		
		list.add(newVo3);
		list.add(newVo1);
		list.add(newVo2);
		
		return list;
	}

	private Object getLimit(String limit) {
		List<String> list=new ArrayList<String>();
		if(DataUtils.isNotEmpty(limit)){
			String[] limitArr=limit.split(",");
			for(int i=0;i<limitArr.length;i++) {
				String str="";
				String[] lArr = limitArr[i].split("-");
				if (lArr.length == 2) {
					if(DataUtils.isEmpty(lArr[0])) {
						lArr[0]="0";
					}
					str=lArr[1]+" >=t.`limit_start` AND t.`limit_end`>="+lArr[0];
					//criteria.andAmountStartLessThan(new BigDecimal(mArr[1])).andAmountEndGreaterThan(new BigDecimal(mArr[0]));
				} else {
					//criteria.andAmountEndGreaterThan(new BigDecimal(mArr[0]));
					str=" t.`limit_end`>="+lArr[0];
				}
				list.add(str);
			}
		}
		return list;
	}

	private List<String> getAmount(String amount) {
		List<String> list=new ArrayList<String>();
		
		if(DataUtils.isNotEmpty(amount)){
			String[] amountArr=amount.split(",");
			for(int i=0;i<amountArr.length;i++) {
				String str="";
				String[] mArr = amountArr[i].split("-");
				if (mArr.length == 2) {
					if(DataUtils.isEmpty(mArr[0])) {
						mArr[0]="0";
					}
					str=mArr[1]+" >=t.`amount_start` AND t.`amount_end`>="+mArr[0];
					//criteria.andAmountStartLessThan(new BigDecimal(mArr[1])).andAmountEndGreaterThan(new BigDecimal(mArr[0]));
				} else {
					//criteria.andAmountEndGreaterThan(new BigDecimal(mArr[0]));
					str=" t.`amount_end`>="+mArr[0];
				}
				list.add(str);
			}
		}
		return list;
	}

	@Override
	public Integer queryOrganizationAllProductList(QueryProductCount queryParam) {
		int count=0;
		if("3".equals(queryParam.getType())) {
			CreditProductCriteria exampleCredit=new CreditProductCriteria();
			Criteria criteria = exampleCredit.createCriteria();
			criteria.andStatusEqualTo(1);
			if(null!=queryParam.getOrganizationId()) {
				criteria.andOrganizationIdEqualTo(queryParam.getOrganizationId());
			}
			if(null!=queryParam.getParentOrganizationId()) {
				criteria.andParentOrganizationIdEqualTo(queryParam.getParentOrganizationId());
			}
			count=count+(int)creditProductMapper.countByExample(exampleCredit);
			
			FinanceProductCriteria exampleFinance=new FinanceProductCriteria();
			com.ark.hngxt.product.domain.FinanceProductCriteria.Criteria criteriaFinance = exampleFinance.createCriteria();
			criteriaFinance.andStatusEqualTo(1);
			if(null!=queryParam.getOrganizationId()) {
				criteriaFinance.andOrganizationIdEqualTo(queryParam.getOrganizationId());
			}
			if(null!=queryParam.getParentOrganizationId()) {
				criteriaFinance.andParentOrganizationIdEqualTo(queryParam.getParentOrganizationId());
			}
			
			count=count+(int)financeProductMapper.countByExample(exampleFinance);
		}else if("1".equals(queryParam.getType())) {
			CreditProductCriteria exampleCredit=new CreditProductCriteria();
			Criteria criteria = exampleCredit.createCriteria();
			criteria.andStatusEqualTo(1);
			if(null!=queryParam.getOrganizationId()) {
				criteria.andOrganizationIdEqualTo(queryParam.getOrganizationId());
			}
			if(null!=queryParam.getParentOrganizationId()) {
				criteria.andParentOrganizationIdEqualTo(queryParam.getParentOrganizationId());
			}
			count=count+(int)creditProductMapper.countByExample(exampleCredit);
		}else if("2".equals(queryParam.getType())) {
			FinanceProductCriteria exampleFinance=new FinanceProductCriteria();
			com.ark.hngxt.product.domain.FinanceProductCriteria.Criteria criteriaFinance = exampleFinance.createCriteria();
			criteriaFinance.andStatusEqualTo(1);
			if(null!=queryParam.getOrganizationId()) {
				criteriaFinance.andOrganizationIdEqualTo(queryParam.getOrganizationId());
			}
			if(null!=queryParam.getParentOrganizationId()) {
				criteriaFinance.andParentOrganizationIdEqualTo(queryParam.getParentOrganizationId());
			}
			count=count+(int)financeProductMapper.countByExample(exampleFinance);
		}
		return Integer.valueOf(count);
	}

	@Override
	@Transactional(rollbackFor = Exception.class,readOnly = false)
	public void updateCreditProductStrategySet(CurrentUser currentUser, UpdateStrategySetVO updateStrategySetVo) {
		CreditProductWithBLOBs record=new CreditProductWithBLOBs();
		BeanCopierUtils.copy(updateStrategySetVo, record);
		record.setUpdateBy(String.valueOf(currentUser.getUserId()));
		creditProductMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	@Transactional(rollbackFor = Exception.class,readOnly = false)
	public void updateFinanceProductStrategySet(CurrentUser currentUser, UpdateStrategySetVO updateStrategySetVo) {
		FinanceProductWithBLOBs record=new FinanceProductWithBLOBs();
		BeanCopierUtils.copy(updateStrategySetVo, record);
		record.setUpdateBy(String.valueOf(currentUser.getUserId()));
		financeProductMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<String> queryAllProductNameList() {
		FinanceProductCriteria exampleFinance=new FinanceProductCriteria();
		exampleFinance.createCriteria().andStatusEqualTo(1);
		List<FinanceProduct> listFinance=financeProductMapper.selectByExample(exampleFinance);
		List<String> nameFinanceList = listFinance.stream().map(FinanceProduct::getName).collect(Collectors.toList());
		
		CreditProductCriteria exampleCredit=new CreditProductCriteria();
		exampleCredit.createCriteria().andStatusEqualTo(1);
		List<CreditProduct> listCredit=creditProductMapper.selectByExample(exampleCredit);
		List<String> nameCreditList=listCredit.stream().map(CreditProduct::getName).collect(Collectors.toList());
		
		List<String> list=new ArrayList<String>();
		list.addAll(nameFinanceList);
		list.addAll(nameCreditList);
		return list;
	}

	@Override
	@Transactional(rollbackFor = Exception.class,readOnly = false)
	public void updateHotCreditProduct(CurrentUser currentUser, HotProductVO hotProductVo) {
		CreditProductWithBLOBs record=new CreditProductWithBLOBs();
		BeanCopierUtils.copy(hotProductVo, record);
		record.setUpdateBy(String.valueOf(currentUser.getUserId()));
		creditProductMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void updateHotFinanceProduct(CurrentUser currentUser, HotProductVO hotProductVo) {
		FinanceProductWithBLOBs record=new FinanceProductWithBLOBs();
		BeanCopierUtils.copy(hotProductVo, record);
		record.setUpdateBy(String.valueOf(currentUser.getUserId()));
		financeProductMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<ProductVO> willLoanCreditProductList() {
//		CreditProductCriteria exampleHot=new CreditProductCriteria();
//		exampleHot.setOrderByClause(" create_date desc ");
//		exampleHot.createCriteria().andStatusEqualTo(1).andBeTopEqualTo(1);
//		List<CreditProduct> hotList = creditProductMapper.selectByExample(exampleHot);
//		
//		List<CreditProductVO> list=new ArrayList<CreditProductVO>();
//		if(DataUtils.isNotEmpty(hotList)) {
//			list=BeanCopierUtils.copyList(hotList, CreditProductVO.class);
//		}
//		CreditProductCriteria example=new CreditProductCriteria();
//		example.setOrderByClause(" apply_count desc ");
//		example.createCriteria().andStatusEqualTo(1).andBeTopEqualTo(0);
//		List<CreditProduct> unHotList = creditProductMapper.selectByExample(example);
//		if(DataUtils.isNotEmpty(unHotList)) {
//			list.addAll(BeanCopierUtils.copyList(unHotList, CreditProductVO.class));
//		}
		
		List<ProductVO> list=new ArrayList<ProductVO>();
		ProductVO pv1=new ProductVO();
		pv1.setName("特色产品");
		pv1.setDescription("为中小微企业提供智能便捷的一站式融资服务");
		pv1.setProductList(bulidProductList("1"));
		
		ProductVO pv2=new ProductVO();
		pv2.setName("信用贷");
		pv2.setDescription("综合展示平台融资对接等信息");
		pv2.setProductList(bulidProductList("2"));
		
		ProductVO pv3=new ProductVO();
		pv3.setName("抵押贷");
		pv3.setProductList(bulidProductList("3"));
		
		ProductVO pv4=new ProductVO();
		pv4.setName("担保贷");
		pv4.setProductList(bulidProductList("4"));
		
		list.add(pv1);
		list.add(pv2);
		list.add(pv3);
		list.add(pv4);
		return list;
	}

	private List<ProductItemVO> bulidProductList(String type) {
		List<ProductItemVO> list=new ArrayList<ProductItemVO>();
		//特色产品
		if("1".equals(type)) {
			ProductItemVO vo1=new ProductItemVO();
			vo1.setId(21L);
			vo1.setName("商业价值贷");
			vo1.setType("1");
			
			ProductItemVO vo2=new ProductItemVO();
			vo2.setId(22L);
			vo2.setName("信补贷");
			vo2.setType("1");
			
			ProductItemVO vo3=new ProductItemVO();
			vo3.setId(1L);
			vo3.setName("助力贷");
			vo3.setType("1");
			
			ProductItemVO vo4=new ProductItemVO();
			vo4.setId(20L);
			vo4.setName("社融贷");
			vo4.setType("1");
			
			list.add(vo1);
			list.add(vo2);
			list.add(vo3);
			list.add(vo4);
		}else if("2".equals(type)) {
			//信用贷
			ProductItemVO vo1=new ProductItemVO();
			vo1.setId(32L);
			vo1.setName("线上税融通");
			vo1.setType("2");
			
			ProductItemVO vo2=new ProductItemVO();
			vo2.setId(11L);
			vo2.setName("纳税e贷");
			vo2.setType("2");
			
			ProductItemVO vo3=new ProductItemVO();
			vo3.setId(31L);
			vo3.setName("湘壹贷");
			vo3.setType("2");
			
			ProductItemVO vo4=new ProductItemVO();
			vo4.setId(25L);
			vo4.setName("易融贷");
			vo4.setType("2");
			
			list.add(vo1);
			list.add(vo2);
			list.add(vo3);
			list.add(vo4);
		}else if("3".equals(type)) {
			//抵押贷
			ProductItemVO vo1=new ProductItemVO();
			vo1.setId(2L);
			vo1.setName("抵押e贷");
			vo1.setType("2");
			
			ProductItemVO vo2=new ProductItemVO();
			vo2.setId(33L);
			vo2.setName("线上抵押贷");
			vo2.setType("2");
			
			list.add(vo1);
			list.add(vo2);
		}else if("4".equals(type)) {
			//担保贷
			ProductItemVO vo1=new ProductItemVO();
			vo1.setId(36L);
			vo1.setName("年审制贷款");
			vo1.setType("2");
			
			list.add(vo1);
		}
		return list;
	}

	@Override
	public List<CreditProductVO> queryCreditProductListWithOutPage(QueryCreditProduct queryParam) {
		CreditProductCriteria example=new CreditProductCriteria();
		example.setOrderByClause(" sort asc,interest_rate_start asc ");
		com.ark.hngxt.product.domain.CreditProductCriteria.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1).andBeMainHotEqualTo(1);
			
		List<CreditProductWithBLOBs> list = creditProductMapper.selectByExampleWithBLOBs(example);
		List<CreditProductVO> retList = getCreditProductVoList(list,false);
		return retList;
	}

	@Override
	@Transactional(rollbackFor = Exception.class,readOnly = false)
	public void addFeedBack(CurrentUser currentUser, AddFeedBackVO feedBack) {
		FeedBack record = new FeedBack();
		BeanCopierUtils.copy(feedBack, record);
		record.setCellPhone(currentUser.getMobilephone());
		record.setCreateBy(String.valueOf(currentUser.getUserId()));
		record.setUserType(currentUser.getUserType().name());
		//企业用户
		if("enterprise".equals(currentUser.getUserType().name())) {
			record.setOrganizationId(currentUser.getEntpId());
		}else if("institutional".equals(currentUser.getUserType().name())) {
			//机构用户
			record.setOrganizationId(currentUser.getInstId());
		}
		feedBackMapper.insertSelective(record);
	}

	@Override
	public ListResult<FeedBackVO> queryFeedBackList(QueryFeedBack queryParam) {

		FeedBackCriteria example=new FeedBackCriteria();
		example.setOrderByClause(" create_date desc");
		com.ark.hngxt.product.domain.FeedBackCriteria.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		if(DataUtils.isNotEmpty(queryParam.getTitle())) {
			criteria.andTitleLike("%"+queryParam.getTitle()+"%");
		}
		if(DataUtils.isNotEmpty(queryParam.getContent())) {
			criteria.andContentLike("%"+queryParam.getContent()+"%");
		}
		if(null!=queryParam.getType()) {
			criteria.andTypeEqualTo(queryParam.getType());
		}
		PageHelper.startPage(queryParam.getPageNum(), queryParam.getPageSize());
		List<FeedBack> list = feedBackMapper.selectByExample(example);
		PageInfo<FeedBack> pageInfo = new PageInfo<FeedBack>(list);
		ListResult<FeedBackVO> listResult = new ListResult<FeedBackVO>();
		listResult.setTotalElements(pageInfo.getTotal());
		listResult.setContent(getFeedBackVoList(list));
		return listResult;
	
	}

	private List<FeedBackVO> getFeedBackVoList(List<FeedBack> list) {
		List<FeedBackVO> voList = new ArrayList<FeedBackVO>();
		Map<String, Object> map = null;
		try {
			map = getOrgByTypeMap(list);
		} catch (Exception e) {
			log.error("查询机构/企业信息error：" + e);
		}
		for (FeedBack bean : list) {
			FeedBackVO target = new FeedBackVO();
			BeanCopierUtils.copy(bean, target);
			target = getFeedBackVo(target,bean.getUserType() ,map);
			voList.add(target);
		}
		return voList;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> getOrgByTypeMap(List<FeedBack> list) {
		List<Long> institutionalIdList = list.stream().filter(item->"institutional".equals(item.getUserType())).map(FeedBack::getOrganizationId).distinct()
				.collect(Collectors.toList());
		Map<String,Object> institutionalMap = getOrganizations(institutionalIdList);
		List<Long> enterpriseIdList = list.stream().filter(item->"enterprise".equals(item.getUserType())).map(FeedBack::getOrganizationId).distinct()
				.collect(Collectors.toList());
		Map<String,Object> enterpriseMap = getEnterprises(enterpriseIdList);
		Map<String, Object> map=new HashMap<String, Object>();
		//institutionName
		if(null!=institutionalMap) {
			map.put("institutionalMap", institutionalMap);
		}
		if(null!=enterpriseMap) {
			map.put("enterpriseMap", enterpriseMap);
		}
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map<String, Object> getEnterprises(List<Long> enterpriseIdList) {
		try {
			if (DataUtils.isNotEmpty(enterpriseIdList)) {
				String url = "http://com-ark-basic-uaa/entps/getByIds";
				ResponseEntity<Map> entity = restTemplate.postForEntity(url, enterpriseIdList, Map.class);
				if (entity.getStatusCode().is2xxSuccessful()) {
					Map res = entity.getBody();
					log.info("获取企业信息列表-success:" + res);
					return res;
				} else {
					log.error("获取企业信息列表-error:" + entity.getBody());
					return null;
				}
			} else {
				log.info("获取企业信息列表：enterpriseIdList为空");
				return null;
			}
		} catch (Exception e) {
			log.error("获取企业信息列表异常："+e);
			return null;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private FeedBackVO getFeedBackVo(FeedBackVO target,String userType,Map<String,Object> map) {
		if(DataUtils.isNotEmpty(target.getFiles())) {
			target.setFilesList(getList(target.getFiles(),","));
		}
		if(DataUtils.isNotEmpty(target.getFilename())) {
			target.setFilenameList(getList(target.getFilename(),","));
		}
		if(null!=map) {
			if("institutional".equals(userType)) {
				if(map.containsKey("institutionalMap")) {
					Map<String,Object> institutionalMap = (Map<String, Object>) map.get("institutionalMap");
					if(null!=institutionalMap&&institutionalMap.containsKey(target.getOrganizationId().toString())) {
						Map institutional=(Map) institutionalMap.get(target.getOrganizationId().toString());
							target.setOrganization(null!=institutional?(String) institutional.get("institutionName"):"");
					}
				}
			}else if("enterprise".equals(userType)) {
				if(map.containsKey("enterpriseMap")) {
					Map<String,Object> enterpriseMap = (Map<String, Object>) map.get("enterpriseMap");
					if(null!=enterpriseMap&&enterpriseMap.containsKey(target.getOrganizationId().toString())) {
						Map enterprise=(Map) enterpriseMap.get(target.getOrganizationId().toString());
							target.setOrganization(null!=enterprise?(String) enterprise.get("entpName"):"");
					}
				}
			}
		}
		return target;
	}

	@Override
	public FeedBackVO queryFeedBackById(Long id) {
		FeedBack bean=feedBackMapper.selectByPrimaryKey(id);
		FeedBackVO target=new FeedBackVO();
		BeanCopierUtils.copy(bean, target);
		target=getFeedBackVo(target,bean.getUserType(),null);
		return target;
	}

	@Override
	@Transactional(rollbackFor = Exception.class,readOnly = false)
	public void updateProductApplyCount(ProductApplyCountDTO dto) {
		try {
			//同步数据到统计服务
			Product p=new Product();
			//特色产品
			if("1".equals(dto.getType())) {
				CreditProductWithBLOBs product = creditProductMapper.selectByPrimaryKey(dto.getProductId());
				if(null!=product) {
					if(product.getApplyCount()!=null) {
						product.setApplyCount(product.getApplyCount()+1);
					}else {
						product.setApplyCount(1L);
					}
					creditProductMapper.updateByPrimaryKeySelective(product);
					p.setApplyCount(product.getApplyCount());
					log.info("更新产品被申请数productId："+dto.getProductId()+",count:"+product.getApplyCount()+1);
				}
			}else if("2".equals(dto.getType())) {
				//金融产品
				 FinanceProductWithBLOBs product = financeProductMapper.selectByPrimaryKey(dto.getProductId());
				if(null!=product) {
					if(product.getApplyCount()!=null) {
						product.setApplyCount(product.getApplyCount()+1);
					}else {
						product.setApplyCount(1L);
					}
					financeProductMapper.updateByPrimaryKeySelective(product);
					p.setApplyCount(product.getApplyCount());
					log.info("更新产品被申请数productId："+dto.getProductId()+",count:"+product.getApplyCount()+1);
				}
			}	
			
			
				p.setProductId(dto.getProductId());
				p.setType(dto.getType());
				productStatistic(p);
		}catch(Exception e) {
			log.error("更新产品被申请数异常：{}"+dto);
		}
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ListResult<RecommendOrganizationVO> queryOrganizationRecommendList(
			QueryOrganizationRecommend queryParam) {
		// 获取机构信息
		List<Map> orgList = getAllOrganization();
		ListResult<RecommendOrganizationVO> listResult = new ListResult<RecommendOrganizationVO>();
		if (DataUtils.isNotEmpty(orgList)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgList", orgList);
            //获取机构评价服务和统计数据
			queryParam=setQueryParamSort(queryParam);
			List<Map> serviceList=getOrganizationService(queryParam);
			map.put("serviceList", serviceList);
			map.put("orgName", queryParam.getOrgName());
			map.put("orgType", queryParam.getOrgType());
			map.put("sortField", queryParam.getSortField());
			map.put("sort", queryParam.getSort());
			PageHelper.startPage(queryParam.getPageNum(), queryParam.getPageSize());
			List<RecommendOrganizationVO> list = new ArrayList<RecommendOrganizationVO>();
			if("1".equals(queryParam.getSortField())) {//首页列表：按服务次数降序排列;
				list=recommendOrganizationMapper.queryOrganizationRecommendList_1(map);
			}else if("2".equals(queryParam.getSortField())||"3".equals(queryParam.getSortField())) {//2机构列表-更多：按照放款金额降序排序，若放款金额相同，则按照贷款笔数降序排；若贷款笔数相同，则按照金融机构入驻时间倒序排序;3机构列表-更多：综合服务得分正序、倒序排序
				list=recommendOrganizationMapper.queryOrganizationRecommendList(map);
			}
			PageInfo<RecommendOrganizationVO> pageInfo = new PageInfo<RecommendOrganizationVO>(list);
			listResult.setTotalElements(pageInfo.getTotal());
			listResult.setContent(getRecommendOrganizationVoList(list));
		} else {
			log.info("机构推荐列表为空");
		}
		return listResult;
	}
private List<RecommendOrganizationVO> getRecommendOrganizationVoList(List<RecommendOrganizationVO> list) {
	for(RecommendOrganizationVO vo:list) {
		vo.setProductCount(vo.getProductCount()!=null?vo.getProductCount():"0");
		vo.setAverageAcceptanceTime(vo.getAverageAcceptanceTime()!=null?vo.getAverageAcceptanceTime():"0");
	}
		return list;
	}

	private QueryOrganizationRecommend setQueryParamSort(QueryOrganizationRecommend queryParam) {
		//sortField 排序字段（1首页列表：按服务次数降序排列;2机构列表-更多：按照放款金额降序排序，若放款金额相同，则按照贷款笔数降序排；若贷款笔数相同，则按照金融机构入驻时间倒序排序;3机构列表-更多：综合服务得分正序、倒序排序
		//sortField==3 sort才需要传值1升序2降序
		String sort = queryParam.getSort();
		String sortField = queryParam.getSortField();
		if (DataUtils.isNotEmpty(sortField)) {
			if("3".equals(sortField)) {
				if(DataUtils.isEmpty(sort)) {//如果sort为空默认为2降序
					sort="2";
				}
			}
		}else {//sortField==2 默认
			queryParam.setSortField("2");
		}
		return queryParam;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Map> getOrganizationService(QueryOrganizationRecommend queryParam) {
		
		try {
			String url = "http://com-ark-hngxt-statistic/eval/free/instOrderList";
			JSONObject ob = new JSONObject();
			ob.put("asc", queryParam.getSort());
			ob.put("orderByType", queryParam.getSortField());
			ob.put("page", queryParam.getPageNum());
			ob.put("size", queryParam.getPageSize());
			log.info("查询机构评价和统计param：" + ob);
			
			ResponseEntity<List> entity = restTemplate.postForEntity(url, ob, List.class);
			if (entity.getStatusCode().is2xxSuccessful()) {
				List res = entity.getBody();
				log.info("获取所有机构评价和统计-success:" + res);
				return res;
			} else {
				log.error("获取所有机构评价和统计-error:" + entity.getBody());
				return null;
			}
		} catch (Exception e) {
			log.error("获取所有机构评价和统计异常："+e);
			return null;
		}
	
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Map> getAllOrganization() {
		try {
			String url = "http://com-ark-basic-uaa/free/institutions/getAllFirstInstitutions";
			ResponseEntity<List> entity = restTemplate.getForEntity(url, List.class);
			if (entity.getStatusCode().is2xxSuccessful()) {
				List res = entity.getBody();
				log.info("获取所有机构-success:" + res);
				return res;
			} else {
				log.error("获取所有机构-error:" + entity.getBody());
				return null;
			}
		} catch (Exception e) {
			log.error("获取所有机构异常："+e);
			return null;
		}
	
	}

	/**
	 *h获取企业申请过的机构
	 */
	@Override
	public RecommendOrganizationVO applyOrganizationHis(QueryOrganizationRecommend queryParam) {
		RecommendOrganizationVO vo = new RecommendOrganizationVO();
		JSONObject json=getHisOrganizationService();
		if(null!=json) {
			Long orgId=json.getLong("id");
			if(null!=orgId&&orgId>0) {
				vo=getOrganizationRecommend(orgId);
			}
		}
		return vo;
	}

	private JSONObject getHisOrganizationService() {
		try {
			String url = "http://com-ark-hngxt-statistic/order/instByOrderNow";
			ResponseEntity<JSONObject> entity = restTemplate.getForEntity(url,JSONObject.class);
			if (entity.getStatusCode().is2xxSuccessful()) {
				JSONObject res = entity.getBody();
				log.info("获取我申请过的机构-success:" + res);
				return res;
			} else {
				log.error("获取我申请过的机构-error:" + entity.getBody());
				return null;
			}
		} catch (Exception e) {
			log.error("获取我申请过的机构异常："+e);
			return null;
		}
	}

	@Override
	public RecommendOrganizationVO getOrganizationRecommend(Long organizationId) {
		RecommendOrganizationVO vo = new RecommendOrganizationVO();
		JSONObject org = getOrganization(organizationId);
		if (null!=org) {
			vo.setOrganizationId(organizationId);
			vo.setName(org.getString("institutionName"));
			//logo取父级的
			if(null!=org.getLong("parentId")&&org.getLong("parentId")>0) {
				JSONObject pOrg = getOrganization(org.getLong("parentId"));
				if (null!=pOrg) {
					vo.setOrganization(pOrg.getString("logoFile"));
					vo.setLogo(pOrg.getString("circleLogoFile"));
				}
			}else {
				vo.setOrganization(org.getString("logoFile"));
				vo.setLogo(org.getString("circleLogoFile"));
			}
			
			vo.setType(org.getString("category"));
			vo.setDescription(org.getString("serviceFields"));
			vo.setConnects(org.getString("connects"));
			vo.setTelephone(org.getString("telephone"));
			vo.setCity(org.getString("city"));
			vo.setProductCount(getOrgProductCount(organizationId));
			//利率范围
			vo.setInterestRateRang(getOrgProductInterestRateRang(organizationId));
			JSONObject orgService = getOrganizationServiceByOrgId(organizationId);
			if (orgService != null) {
				vo.setAverageAcceptanceTime(orgService.getString("acceptanceDayAvg"));
				vo.setLoanAmount(orgService.getBigDecimal("ryMoney"));
				vo.setValuationCount(orgService.getInteger("evalCount"));
				vo.setLoanCount(orgService.getInteger("ryCount"));
				vo.setServiceCount(orgService.getInteger("fwCount"));
				vo.setIntegratedServicesStr(orgService.getString("integratedServicesStr"));
				vo.setServiceEvaluationAvg(orgService.getInteger("serviceEvaluationAvg"));
				vo.setServiceEvaluationHigher(orgService.getInteger("serviceEvaluationHigher"));
				vo.setMajorEvaluationAvg(orgService.getInteger("majorEvaluationAvg"));
				vo.setMajorEvaluationHigher(orgService.getInteger("majorEvaluationHigher"));
				vo.setDurationEvaluationAvg(orgService.getInteger("durationEvaluationAvg"));
				vo.setDurationEvaluationHigher(orgService.getInteger("durationEvaluationHigher"));
				vo.setServiceScore(orgService.getInteger("integratedServices")==null?"":orgService.getInteger("integratedServices").toString());
			}
		}
		return vo;
	}

	private String getOrgProductInterestRateRang(Long organizationId) {
		
		String interestRateRang="";
		
		try {
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("orgId", organizationId);
			interestRateRang = recommendOrganizationMapper.orgProductInterestRateRang(map);
			log.info("获取机构利率范围："+interestRateRang);
		}catch (Exception e) {
			log.error("获取机构利率范围异常："+e);
		}
		return interestRateRang;
	}

	private JSONObject getOrganizationServiceByOrgId(Long organizationId) {
		try {
			String url = "http://com-ark-hngxt-statistic/eval/free/instEval/"+organizationId;
			log.info("通过机构id获取机构评价和统计-start:" + url);
			ResponseEntity<JSONObject> entity = restTemplate.getForEntity(url,JSONObject.class);
			if (entity.getStatusCode().is2xxSuccessful()) {
				JSONObject res = entity.getBody();
				log.info("通过机构id获取机构评价和统计-success:" + res);
				return res;
			} else {
				log.error("通过机构id获取机构评价和统计-error:" + entity.getBody());
				return null;
			}
		} catch (Exception e) {
			log.error("通过机构id获取机构评价和统计异常："+e);
			return null;
		}
	}

	private String getOrgProductCount(Long organizationId) {
		QueryProductCount queryParam = new QueryProductCount();
		queryParam.setParentOrganizationId(organizationId);
		queryParam.setType("3");
		Integer count = queryOrganizationAllProductList(queryParam);
		return String.valueOf(count);
	}

	@Override
	public Integer getProductStatus(Long id, String type) {
		int status=4;
		if("1".equals(type)) {
			CreditProductWithBLOBs bean = creditProductMapper.selectByPrimaryKey(id);
			if(null!=bean) {
				status=bean.getStatus();
			}
		}else if("2".equals(type)) {
			FinanceProductWithBLOBs bean = financeProductMapper.selectByPrimaryKey(id);
			if(null!=bean) {
				status=bean.getStatus();
			}
		}
		return status;
	}

	@Override
	public List<ProductCommonVO> getAllProduct() {
		List<ProductCommonVO> list=financeProductMapper.getAllProduct();
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ListResult<RecommendOrganizationVO> queryHotOrganizationRecommendList(
			QueryOrganizationRecommend queryParam) {
		// 获取机构信息
		List<Map> orgList = getAllOrganization();
		if (DataUtils.isNotEmpty(orgList)) {
			orgList=orgList.parallelStream().filter(o->o.containsKey("hotRecommend")&&null!=o.get("hotRecommend")&&(Boolean)o.get("hotRecommend")).collect(Collectors.toList());
		}
		ListResult<RecommendOrganizationVO> listResult = new ListResult<RecommendOrganizationVO>();
		if (DataUtils.isNotEmpty(orgList)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgList", orgList);
            //获取机构评价服务和统计数据
			queryParam=setQueryParamSort(queryParam);
			List<Map> serviceList=getOrganizationService(queryParam);
			//serviceList=gethahaha();
			map.put("serviceList", serviceList);
			PageHelper.startPage(queryParam.getPageNum(), queryParam.getPageSize());
			List<RecommendOrganizationVO> list=recommendOrganizationMapper.queryHotOrganizationRecommendList(map);
			
			PageInfo<RecommendOrganizationVO> pageInfo = new PageInfo<RecommendOrganizationVO>(list);
			listResult.setTotalElements(pageInfo.getTotal());
			listResult.setContent(getRecommendOrganizationVoList(list));
		} else {
			log.info("机构推荐列表为空");
		}
		return listResult;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<ScreeningConditionsVO> financeProductScreeningConditionsList(String terminal) {
		List<ScreeningConditionsVO> voList=new ArrayList<ScreeningConditionsVO>();
		
		List<ScreeningConditionsItem> orgType=getDictList("org_type",terminal,"1");//机构类型
		if(null!=orgType&&orgType.size()>0) {
			ScreeningConditionsVO vo=new ScreeningConditionsVO();
			vo.setList(orgType);
			vo.setType(getDictNameByCode("org_type"));
			vo.setCode("organizationType");
			voList.add(vo);
		}
		
		List<ScreeningConditionsItem> guaranteeType=getDictList("guarantee_type",terminal,"1");//担保方式
		if(null!=guaranteeType&&guaranteeType.size()>0) {
			ScreeningConditionsVO vo=new ScreeningConditionsVO();
			vo.setList(guaranteeType);
			vo.setType(getDictNameByCode("guarantee_type"));
			vo.setCode("guaranteeType");
			voList.add(vo);
		}
		
		List<ScreeningConditionsItem> limit=getDictList("finance_limit",terminal,"");//融资期限
		if(null!=limit&&limit.size()>0) {
			ScreeningConditionsVO vo=new ScreeningConditionsVO();
			vo.setList(limit);
			vo.setType(getDictNameByCode("finance_limit"));
			vo.setCode("limit");
			voList.add(vo);
		}
		
		List<ScreeningConditionsItem> amount=getDictList("finance_amount",terminal,"");//融资金额
		if(null!=amount&&amount.size()>0) {
			ScreeningConditionsVO vo=new ScreeningConditionsVO();
			vo.setList(amount);
			vo.setType(getDictNameByCode("finance_amount"));
			vo.setCode("amount");
			voList.add(vo);
		}
		
		List<ScreeningConditionsItem> area=getDictList("province_0",terminal,"1");//申请区域：目前是湖南省内
		if(null!=area&&area.size()>0) {
			ScreeningConditionsVO vo=new ScreeningConditionsVO();
			vo.setList(area);
			vo.setType("申请区域");
			vo.setCode("applyArea");
			voList.add(vo);
		}
		//银行-一级
			List<Map> orgMap = getAllOrganization();
			if(null!=orgMap&&orgMap.size()>0) {
				ScreeningConditionsVO vo=new ScreeningConditionsVO();
				vo.setList(getOrgScreeningConditionsVO(orgMap,terminal));
				vo.setType("银行名称");
				vo.setCode("organizationId");
				voList.add(vo);
			}
		return voList;
	}

	@SuppressWarnings("rawtypes")
	private List<ScreeningConditionsItem> getOrgScreeningConditionsVO(List<Map> orgMap,String terminal) {
		List<ScreeningConditionsItem> list=new ArrayList<ScreeningConditionsItem>();
		if(DataUtils.isNotEmpty(orgMap)) {
			if("1".equals(terminal)) {
				ScreeningConditionsItem common=new ScreeningConditionsItem();
				common.setName("不限");
				common.setValue("");
				list.add(common);
			}
			for(Map t:orgMap) {
				ScreeningConditionsItem vo=new ScreeningConditionsItem();
				vo.setName((String)t.get("institutionName"));
				vo.setValue(String.valueOf((Integer)t.get("institutionId")));
				list.add(vo);
			}
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	private String getDictNameByCode(String code) {
		try {
			List<String> codeList=new ArrayList<String>();
			codeList.add(code);
			String url = "http://com-ark-comm-sysconfig/free/dictionary/getMapByCodes";
			ResponseEntity<Map> entity = restTemplate.postForEntity(url,codeList, Map.class);
			if (entity.getStatusCode().is2xxSuccessful()) {
				Map res = entity.getBody();
				log.info("获取参数字典根信息code["+code+"]-success:" + res);
				if(DataUtils.isNotEmpty(res)) {
					return null!=res.get(code)?(String) res.get(code):"";	
				}
				return "";
			} else {
				log.error("获取参数字典根信息code["+code+"]-error:" + entity.getBody());
				return null;
			}
		} catch (Exception e) {
			log.error("获取参数字典根信息code["+code+"]异常："+e);
			return null;
		}
	}

	/**根据code获取第一级子节点集合
	 * @param type
	 * @param dataType:1取code 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<ScreeningConditionsItem> getDictList(String type,String terminal,String dataType) {
		List<Map> mapList=getDictListByCode(type);
		List<ScreeningConditionsItem> list=new ArrayList<ScreeningConditionsItem>();
		if(DataUtils.isNotEmpty(mapList)) {
			if("1".equals(terminal)) {
				ScreeningConditionsItem common=new ScreeningConditionsItem();
				common.setName("不限");
				common.setValue("");
				list.add(common);
			}
			for(Map t:mapList) {
				ScreeningConditionsItem vo=new ScreeningConditionsItem();
				vo.setName((String)t.get("name"));
				if("1".equals(dataType)) {
					vo.setValue((String)t.get("code"));
				}else {
					vo.setValue((String)t.get("remark"));
				}
				list.add(vo);
			}
		}
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Map> getDictListByCode(String code) {
		try {
			String url = "http://com-ark-comm-sysconfig/free/dictionary/getDictionaryByCode?code="+code;
			ResponseEntity<List> entity = restTemplate.getForEntity(url, List.class);
			if (entity.getStatusCode().is2xxSuccessful()) {
				List res = entity.getBody();
				log.info("获取参数字典code["+code+"]-success:" + res);
				return res;
			} else {
				log.error("获取参数字典code["+code+"]-error:" + entity.getBody());
				return null;
			}
		} catch (Exception e) {
			log.error("获取参数字典code["+code+"]异常："+e);
			return null;
		}
	}

	@Override
	public Map<String,Object> getOrgProductInfo(Long id) {
		Map<String,Object> map=recommendOrganizationMapper.getOrgProductInfo(id);
		return map;
	}

	@Override
	@Transactional(rollbackFor = Exception.class,readOnly = false)
	public void addFeedBackApp(CurrentUser currentUser, AddFeedBackAppVO feedBack) {
		if(DataUtils.isNotEmpty(feedBack.getFiles())&&feedBack.getFiles().split(",").length>3) {
			throw new RestApiException("最多上传3张图片");
		}
		FeedBack record = new FeedBack();
		BeanCopierUtils.copy(feedBack, record);
		record.setCellPhone(currentUser.getMobilephone());
		record.setCreateBy(String.valueOf(currentUser.getUserId()));
		record.setUserType(currentUser.getUserType().name());
		//record.setContent(EmojiParser.removeAllEmojis(record.getContent()));
		if("enterprise".equals(currentUser.getUserType().name())) {//企业用户
			record.setOrganizationId(currentUser.getEntpId());
		}else if("institutional".equals(currentUser.getUserType().name())) {//机构用户
			record.setOrganizationId(currentUser.getInstId());
		}
		feedBackMapper.insertSelective(record);
	}

	@Override
	public List<ScreeningConditionsItem> feedBackDictList(String code) {
		List<ScreeningConditionsItem> list =new ArrayList<ScreeningConditionsItem>();
		if(DataUtils.isNotEmpty(code)) {
			list= getDictList(code, "","");
		}
		return list;
	}

	@Override
	public String pushProduct(ProductToBigData product) {
		long pbdId = IdWorker.getId();
		product.setType(new String[] { "productTags" });
		product.setTrxId(pbdId);
		
		ProductBigData pbd=new ProductBigData();
		pbd.setId(pbdId);
		pbd.setType("tagMatch");
		pbd.setRequestBody(JSONObject.toJSONString(product));
		String result = mqProducerService.send(product, "PRODUCT_TAG_BACK");
		pbd.setMsgId(result);
		productBigDataMapper.insert(pbd);
		return result;
	}

	@Override
	public void updateProductApplyAmount(ProductApplyCountDTO dto) {
		try {
			//同步数据到统计服务
			//Product p=new Product();
			//特色产品
			if("1".equals(dto.getType())) {
				CreditProductWithBLOBs product = creditProductMapper.selectByPrimaryKey(dto.getProductId());
				if(null!=product) {
					if(product.getTotalAmount()!=null) {
						product.setTotalAmount(product.getTotalAmount().add(dto.getAmount()));
					}else {
						product.setTotalAmount(dto.getAmount());
					}
					creditProductMapper.updateByPrimaryKeySelective(product);
					//p.setApplyCount(product.getApplyCount());
					log.info("更新产品被申请金额productId："+dto.getProductId()+",amount:"+product.getTotalAmount());
				}
			}else if("2".equals(dto.getType())) {
				//金融产品
				 FinanceProductWithBLOBs product = financeProductMapper.selectByPrimaryKey(dto.getProductId());
				if(null!=product) {
					if(product.getTotalAmount()!=null) {
						product.setTotalAmount(product.getTotalAmount().add(dto.getAmount()));
					}else {
						product.setTotalAmount(dto.getAmount());
					}
					financeProductMapper.updateByPrimaryKeySelective(product);
					//p.setApplyCount(product.getApplyCount());
					log.info("更新产品被申请金额productId："+dto.getProductId()+",amount:"+product.getTotalAmount());
				}
			}	
//				p.setProductId(dto.getProductId());
//				p.setType(dto.getType());
//				productStatistic(p);
		}catch(Exception e) {
			log.error("更新产品被申请金额异常：{}"+dto);
		}
	}

	@Override
	public void updateFinanceProductOrganizationType(ProductOrganizationTypeDTO dto) {
		FinanceProductWithBLOBs record=new FinanceProductWithBLOBs();
		record.setOrganizationType(dto.getType());
		FinanceProductCriteria example=new FinanceProductCriteria();
		example.createCriteria().andParentOrganizationIdEqualTo(dto.getOrgId());
		int result=financeProductMapper.updateByExampleSelective(record, example);
		log.info("更新金融产品机构类型："+result);
	}
}
