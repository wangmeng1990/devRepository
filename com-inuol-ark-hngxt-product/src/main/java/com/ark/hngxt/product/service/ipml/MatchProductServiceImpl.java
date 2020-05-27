package com.ark.hngxt.product.service.ipml;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.ark.dependencies.common.auth.model.CurrentUser;
import com.ark.dependencies.common.auth.model.ListResult;
import com.ark.hngxt.product.domain.CreditProduct;
import com.ark.hngxt.product.domain.CreditProductCriteria;
import com.ark.hngxt.product.domain.CreditProductWithBLOBs;
import com.ark.hngxt.product.domain.EntpProductMatch;
import com.ark.hngxt.product.domain.FinanceProduct;
import com.ark.hngxt.product.domain.FinanceProductCriteria;
import com.ark.hngxt.product.domain.FinanceProductWithBLOBs;
import com.ark.hngxt.product.domain.MatchProduct;
import com.ark.hngxt.product.mapper.CreditProductMapper;
import com.ark.hngxt.product.mapper.EntpProductMatchMapper;
import com.ark.hngxt.product.mapper.FinanceProductMapper;
import com.ark.hngxt.product.mapper.MatchProductMapper;
import com.ark.hngxt.product.model.CreditProductVO;
import com.ark.hngxt.product.model.ProductIntelligentMatchVO;
import com.ark.hngxt.product.model.QueryCreditProduct;
import com.ark.hngxt.product.model.intelligent.match.EnterpriseTag;
import com.ark.hngxt.product.service.MatchProductService;
import com.ark.hngxt.product.service.ProductService;
import com.ark.hngxt.product.util.BeanCopierUtils;
import com.ark.hngxt.product.util.DataUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangmeng
 *
 */
@Service
@Slf4j
public class MatchProductServiceImpl extends ServiceImpl<MatchProductMapper, MatchProduct> implements MatchProductService {
	@Autowired
	private ProductService productService;
	@Autowired
    private RestTemplate restTemplate;
	@Autowired
	private MatchProductMapper matchProductMapper;
	@Autowired
	private EntpProductMatchMapper entpProductMatchMapper;
	@Autowired
	private CreditProductMapper creditProductMapper;
	@Autowired
	private FinanceProductMapper financeProductMapper;
	
	@Override
	public List<ProductIntelligentMatchVO> queryProductIntelligentMatchList(CurrentUser currentUser,String type) {
		List<ProductIntelligentMatchVO> pimList=new ArrayList<ProductIntelligentMatchVO>();
		if(null!=currentUser&&null!=currentUser.getEntpId()) {//企业已登录
			Long entpId = currentUser.getEntpId();
			List<String> tagList = queryEntpTagsByEntpId(entpId);//查询企业标签
			if(null!=tagList&&tagList.size()>0) {
				List<MatchProduct> matchProductList = matchProductMapper.queryMatchProduct();
				if(DataUtils.isNotEmpty(matchProductList)) {
					//更新产品被申请金额
					updateProductTotalAmount(matchProductList);
					//根据产品标签去匹配企业标签tagList，更新entp_product_match
					updateEntpProductMatch(matchProductList,tagList,entpId);
					//查询匹配产品列表
					pimList= entpProductMatchMapper.queryMatchProducList(entpId);
					if(DataUtils.isNotEmpty(pimList)) {
						if(pimList.size()>=6) {
							pimList=pimList.subList(0, 6);
							pimList=buildList(pimList);
						}else {
							//小于6，查询推荐的特色产品补充为6个
							List<ProductIntelligentMatchVO> hotCreditList = queryHotCreditProduct(type);
							pimList=buildMatchProduct(pimList,hotCreditList);
							pimList=buildList(pimList);//构造数据
						}
					}else {
						pimList = queryHotCreditProduct(type);
					}
				}else {
					pimList = queryHotCreditProduct(type);
				}
			}else {//查询推荐的特色产品补充为6个
				pimList = queryHotCreditProduct(type);
			}
		}else {
			//查询推荐的特色产品补充为6个
			 pimList = queryHotCreditProduct(type);
		}
		return pimList;
	}
	

	@SuppressWarnings({ "rawtypes", "unlikely-arg-type" })
	private void updateProductTotalAmount(List<MatchProduct> matchProductList) {
		List<Long> creditIdList=matchProductList.stream().filter(t->"1".equals(t.getProductType())).map(MatchProduct::getProductId).distinct()
				.collect(Collectors.toList());
		List<Long> financeIdList=matchProductList.stream().filter(t->"2".equals(t.getProductType())).map(MatchProduct::getProductId).distinct()
				.collect(Collectors.toList());
		
		
		if(DataUtils.isNotEmpty(creditIdList)) {
			try {
				Map<String, Object> creditMap = getCreditTotalAmountMap(creditIdList);
				if(null!=creditMap&&creditMap.size()>0) {
					CreditProductCriteria example=new CreditProductCriteria();
					example.createCriteria().andIdIn(creditIdList).andStatusEqualTo(1);
					List<CreditProduct> list=creditProductMapper.selectByExample(example);
					if(DataUtils.isNotEmpty(list)) {
						log.info("list size={}", list.size());
						for(CreditProduct credit:list) {
							log.info("string={}", creditMap.containsKey(credit.getId().toString()));
							log.info("long={}", creditMap.containsKey(credit.getId()));
							if(creditMap.containsKey(credit.getId().toString())) {
								log.info("--------------------{}", credit.getId());
								Map map = (Map)creditMap.get(credit.getId().toString());
								log.info("map={}", map);
								if(null!=map) {
									CreditProductWithBLOBs record=new CreditProductWithBLOBs();
									record.setId(credit.getId());
									record.setTotalAmount(new BigDecimal((Double)map.get("money")));
									creditProductMapper.updateByPrimaryKeySelective(record);
								}
							}
						}
					}
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
       if(DataUtils.isNotEmpty(financeIdList)) {
			try {
				Map<String, Object> financeMap = getFinanceTotalAmountMap(financeIdList);
				if(null!=financeMap&&financeMap.size()>0) {
					FinanceProductCriteria example=new FinanceProductCriteria();
					example.createCriteria().andIdIn(financeIdList).andStatusEqualTo(1);
					List<FinanceProduct> list=financeProductMapper.selectByExample(example);
					if(DataUtils.isNotEmpty(list)) {
						for(FinanceProduct finance:list) {
							if(financeMap.containsKey(finance.getId().toString())) {
								Map map = (Map)financeMap.get(finance.getId().toString());
								if(null!=map) {
									FinanceProductWithBLOBs record=new FinanceProductWithBLOBs();
									record.setId(finance.getId());
									record.setTotalAmount(new BigDecimal((Double)map.get("money")));
									financeProductMapper.updateByPrimaryKeySelective(record);
								}
							}
						}
					}
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String, Object> getFinanceTotalAmountMap(List<Long> creditIdList) {
		Map<String, Object> countMap = null;
		String url = "http://com-ark-hngxt-statistic/order/free/productOrderSummary2";
		JSONObject ob = new JSONObject();
		ob.put("pidArr", creditIdList);
		log.info("查询金融产品申请放款额param：" + ob);
		HttpHeaders requestHeaders = new HttpHeaders();
	    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<JSONObject> requestEntity = new HttpEntity<JSONObject>(ob, requestHeaders);
		ResponseEntity<Map> entity = restTemplate.postForEntity(url, requestEntity, Map.class);
		if (entity.getStatusCode().is2xxSuccessful()) {
			countMap= entity.getBody();
			log.info("查询金融产品申请放款额return-countMap:" + countMap);
		}else {
			log.error("查询金融产品申请放款额error-countMap：" + entity.getBody());
		}
		return countMap;
	}


	private List<ProductIntelligentMatchVO> buildMatchProduct(List<ProductIntelligentMatchVO> pimList,
			List<ProductIntelligentMatchVO> hotCreditList) {
		//筛选特色产品，用与补充特色产品时去重
		List<Long> pimIdList=pimList.stream().filter(t->"1".equals(t.getOrderType())).map(ProductIntelligentMatchVO::getId).collect(Collectors.toList());
		if(DataUtils.isNotEmpty(pimIdList)) {//直接补充
			int supplemenTimes = 6-pimList.size();
			if(DataUtils.isNotEmpty(hotCreditList)) {
				for(int i=0;i<hotCreditList.size();i++) {
					pimList.add(hotCreditList.get(i));
					if(i==(supplemenTimes-1)) {
						break;
					}
				}
			}
		}else {
			if(DataUtils.isNotEmpty(hotCreditList)) {
				//去重已匹配的特色产品
				hotCreditList=hotCreditList.stream().filter(t->!pimIdList.contains(t.getId())).collect(Collectors.toList());
				 int supplemenTimes = 6-pimList.size();
				 if(DataUtils.isNotEmpty(hotCreditList)) {
						for(int i=0;i<hotCreditList.size();i++) {
							pimList.add(hotCreditList.get(i));
							if(i==(supplemenTimes-1)) {
								break;
							}
						}
				 }
			}	
		}
		return pimList;
	}

	
	/**构造数据
	 * @param pimList
	 * @return
	 */
	private List<ProductIntelligentMatchVO> buildList(List<ProductIntelligentMatchVO> pimList) {
		List<ProductIntelligentMatchVO> newList=new ArrayList<ProductIntelligentMatchVO>();
		
		for(ProductIntelligentMatchVO p:pimList) {
			p=buildProductIntelligentMatchVO(p);
			newList.add(p);
		}
		return newList;
	}

	private ProductIntelligentMatchVO buildProductIntelligentMatchVO(ProductIntelligentMatchVO target) {

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
		if(DataUtils.isNotEmpty(target.getFeature())) {
			target.setFeatureList(getList(target.getFeature(),";"));
		}
		if(DataUtils.isNotEmpty(target.getTag())) {
			target.setTagList(getList(target.getTag(),","));
		}
		
		JSONObject organization=getOrganization(target.getOrganizationId());
		JSONObject parentOrganization=getOrganization(target.getParentOrganizationId());
		target.setOrganization(null!=organization?organization.getString("institutionName"):"");
		target.setParentOrganization(null!=parentOrganization?parentOrganization.getString("institutionName"):"");
		target.setOrganizationLogo(null!=parentOrganization?parentOrganization.getString("logoFile"):"");
		target.setLogo(null!=parentOrganization?parentOrganization.getString("circleLogoFile"):"");
		return target;
	}

	private List<String> getList(String param,String split) {
		List<String> list=new ArrayList<String>();
		if(DataUtils.isNotEmpty(param)) {
			String[] arr = param.split(split);
			list=Arrays.asList(arr);
		}
		return list;
	}

	private void updateEntpProductMatch(List<MatchProduct> matchProductList,List<String> tagList,Long entpId) {
		//企业与产品的标签匹配只保留最新的记录
		EntityWrapper<EntpProductMatch> wrapper=new EntityWrapper<EntpProductMatch>();
		wrapper.eq("entp_id", entpId);
		entpProductMatchMapper.delete(wrapper);
		//企业与产品的标签匹配只保留最新的记录
		for(MatchProduct mp:matchProductList) {//根据产品标签去匹配企业标签tagList，更新entp_product_match
			//插入匹配的新记录
			EntpProductMatch entity=new EntpProductMatch();
			entity.setId(IdWorker.getId());
			entity.setEntpId(entpId);
			entity.setProductId(mp.getProductId());
			entity.setProductType(mp.getProductType());
			entity.setEntpTags(StringUtils.join(tagList, ";"));
			entity.setProductTags(mp.getTags());
			entity.setMatchCount(caclMatchCount(tagList,mp.getTags()));
			entpProductMatchMapper.insert(entity);
			//插入匹配的新记录
		}
	}
	private Integer caclMatchCount(List<String> tagList, String productTags) {
		int matchCount=0;
		if(DataUtils.isNotEmpty(productTags)&&DataUtils.isNotEmpty(tagList)) {
			String[] productTagArr = productTags.split(";");
			List<String> productTagList = new ArrayList<>(Arrays.asList(productTagArr));
			productTagList.retainAll(tagList);
			matchCount=productTagList.size();
		}
		return matchCount;
	}
	private List<ProductIntelligentMatchVO> queryHotCreditProduct(String type) {
		QueryCreditProduct queryParam = new QueryCreditProduct();
		queryParam.setStatus(1);
		if("1".equals(type)) {//pc
			queryParam.setBeHot(1);
			queryParam.setOrderByClause(" be_main_hot_sort asc,create_date asc ");
		}else if("2".equals(type)){//app
			queryParam.setBeMainHotApp(1);
			queryParam.setOrderByClause(" be_main_hot_app_sort asc,create_date asc ");
		}
		queryParam.setPageNum(1);
		queryParam.setPageSize(6);
		ListResult<CreditProductVO> page = productService.queryCreditProductList(queryParam);
		List<CreditProductVO> cvl=page.getContent();
		List<ProductIntelligentMatchVO> list=new ArrayList<ProductIntelligentMatchVO>();
		if(null!=cvl&&cvl.size()>0) {
			list=BeanCopierUtils.copyList(cvl, ProductIntelligentMatchVO.class);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	private List<String> queryEntpTagsByEntpId(Long entpId) {
		EnterpriseTag entp=queryEntpByEntpId(entpId);
		List<String> tagList=new ArrayList<String>();
		if(null!=entp) {
			JSONObject jsonObject = JSONObject.parseObject(entp.getOutputData());
	        if(jsonObject.containsKey("success")&&jsonObject.getBooleanValue("success")) {
	        	JSONObject companyTagObj = jsonObject.getJSONObject("companyTags");
		         tagList = (List<String>) companyTagObj.get("companyTagsList");
		        log.info("获取企业标签[id="+entpId+"]tagList----------------------:" + tagList + "-------------------结束");
	        }
		}
		return tagList;
	}
	private EnterpriseTag queryEntpByEntpId(Long entpId) {
		try {
			String url = "http://com-ark-basic-uaa/entps/queryEntpTagsByEntpId?entpId=" + entpId;
			log.info("通过id获取企业信息-start:" + url);
			ResponseEntity<EnterpriseTag> entity = restTemplate.getForEntity(url,EnterpriseTag.class);
			if (entity.getStatusCode().is2xxSuccessful()) {
				EnterpriseTag res = entity.getBody();
				log.info("通过id获取企业信息-success:" + res);
				return res;
			} else {
				log.error("通过id获取企业信息-error:" + entity.getBody());
				return null;
			}
		} catch (Exception e) {
			log.error("通过id获取企业信息异常："+e);
			return null;
		}
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String, Object> getCreditTotalAmountMap(List<Long> idList) throws Exception{
		Map<String, Object> countMap = null;
		String url = "http://com-ark-hngxt-statistic/order/free/productOrderSummary";
		JSONObject ob = new JSONObject();
		ob.put("pidArr", idList);
		log.info("查询特色产品申请放款额param：" + ob);
		HttpHeaders requestHeaders = new HttpHeaders();
	    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<JSONObject> requestEntity = new HttpEntity<JSONObject>(ob, requestHeaders);
		ResponseEntity<Map> entity = restTemplate.postForEntity(url, requestEntity, Map.class);
		if (entity.getStatusCode().is2xxSuccessful()) {
			countMap= entity.getBody();
			log.info("查询特色产品申请放款额return-countMap:" + countMap);
		}else {
			log.error("查询特色产品申请放款额error-countMap：" + entity.getBody());
		}
		return countMap;
	}
}
