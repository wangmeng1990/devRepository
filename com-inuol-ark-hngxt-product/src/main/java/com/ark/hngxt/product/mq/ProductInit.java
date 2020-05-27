package com.ark.hngxt.product.mq;


import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ark.hngxt.product.domain.CreditProductCriteria;
import com.ark.hngxt.product.domain.CreditProductWithBLOBs;
import com.ark.hngxt.product.domain.FinanceProductCriteria;
import com.ark.hngxt.product.domain.FinanceProductWithBLOBs;
import com.ark.hngxt.product.mapper.CreditProductMapper;
import com.ark.hngxt.product.mapper.FinanceProductMapper;
import com.ark.hngxt.product.model.intelligent.match.ProductToBigData;
import com.ark.hngxt.product.service.ProductService;
import com.ark.hngxt.product.util.BeanCopierUtils;

import lombok.extern.slf4j.Slf4j;

/**重启应用:推送产品信息到大数据用于打标签
 * @author wangmeng
 *
 */
@Slf4j
@Component
public class ProductInit implements InitializingBean {
	@Autowired
	private ProductService productService;
	@Autowired
	private FinanceProductMapper financeProductMapper;
	@Autowired
	private CreditProductMapper creditProductMapper;
	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			productInit();
		}catch(Exception e) {
			log.error("推送产品信息到大数据-error:"+e.getMessage());
		}
	}
	private void productInit() {
		log.info("推送特色产品信息到大数据-start");
		CreditProductCriteria exampleCredit=new CreditProductCriteria();
		exampleCredit.createCriteria().andStatusEqualTo(1);
		List<CreditProductWithBLOBs> creditList = creditProductMapper.selectByExampleWithBLOBs(exampleCredit);
		for(CreditProductWithBLOBs credit:creditList) {
			try{
				ProductToBigData product=new ProductToBigData();
				BeanCopierUtils.copy(credit, product);
				product.setFinanceType("特色产品");
				product.setProductType("1");
				product.setProductId(credit.getId());
				productService.pushProduct(product);
			}catch(Exception e) {
				log.info("推送特色产品产品信息到大数据-error:[id="+credit.getId()+"]");
			}
			
		}
		log.info("推送特色产品产品信息到大数据-end");
		
		log.info("推送金融产品信息到大数据-start");
		FinanceProductCriteria exampleFinance=new FinanceProductCriteria();
		exampleFinance.createCriteria().andStatusEqualTo(1);
		List<FinanceProductWithBLOBs> financeList = financeProductMapper.selectByExampleWithBLOBs(exampleFinance);
		for(FinanceProductWithBLOBs finance:financeList) {
			try{
				ProductToBigData product=new ProductToBigData();
				BeanCopierUtils.copy(finance, product);
				product.setFinanceType(finance.getType());
				product.setProductType("2");
				product.setProductId(finance.getId());
				productService.pushProduct(product);
			}catch(Exception e) {
				log.info("推送金融产品产品信息到大数据-error:[id="+finance.getId()+"]");
			}
		}
		log.info("推送金融产品产品信息到大数据-end");
	}
}
