package com.ark.hngxt.product.mq;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ark.dependencies.common.mq.MqConsumer;
import com.ark.hngxt.product.domain.MatchProduct;
import com.ark.hngxt.product.domain.ProductBigData;
import com.ark.hngxt.product.mapper.MatchProductMapper;
import com.ark.hngxt.product.mapper.ProductBigDataMapper;
import com.ark.hngxt.product.util.DataUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProductConsumer extends MqConsumer {
 
	@Value("${rabbitmq.topic.ark_common_callback}")
    private String topic;
	
	@Autowired
	private ProductBigDataMapper productBigDataMapper;
	@Autowired
	private MatchProductMapper matchProductMapper;
	
	@PostConstruct
    public void setTopic() {
        this.setTopic(topic);
        this.setGroupId("GID_PRODUCT_TAG");
        this.setMessageTag("PRODUCT_TAG_BACK");
        log.info("----------------------" + this.getMQClient());
    }
	 
	@Override
	public void callback(String json) {
		// TODO Auto-generated method stub
		log.info("产品标签匹配回调消息：json={}", json);
		//保存交互数据
		ProductBigData pbd=new ProductBigData();
		if (json == null && "".equals(json)) {
			pbd.setStatus(0);
            log.warn("产品标签匹配回调数据为null");
           return;
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
        Long trxId=jsonObject.getLong("trxId");
        pbd.setId(trxId);
        pbd.setMsg(json);
		productBigDataMapper.updateById(pbd);
		//产品标签匹配表
		updateMatchProduct(jsonObject);
	}

	private void updateMatchProduct(JSONObject jsonObject) {
	if(null!=jsonObject) {
		if(jsonObject.containsKey("success")) {
			Boolean success=jsonObject.getBoolean("success");
			if(success) {
				
				//存在产品id和产品类型productType和匹配的标签tag才保存数据
				if(jsonObject.containsKey("product_tag")&&jsonObject.containsKey("productId")&&jsonObject.containsKey("productType")) {
					JSONArray tagArr = jsonObject.getJSONArray("product_tag");
					String id=jsonObject.getString("productId");
					String productType=jsonObject.getString("productType");
					if(null!=tagArr&&tagArr.size()>0&&DataUtils.isNotEmpty(id)&&DataUtils.isNotEmpty(productType)) {
						
						//每次更新之前匹配的置为无效
						EntityWrapper<MatchProduct> wrapper=new EntityWrapper<MatchProduct>();
						wrapper.eq("product_id", Long.valueOf(id));
						wrapper.eq("product_type", productType);
						matchProductMapper.delete(wrapper);
						//每次更新之前匹配的置为无效
						MatchProduct mp=new MatchProduct();
						mp.setId(IdWorker.getId());
						mp.setProductId(Long.valueOf(id));
						mp.setProductType(productType);
						JSONArray tag = jsonObject.getJSONArray("product_tag");
						List<String> tagList = tag.toJavaList(String.class);
						String tags=StringUtils.join(tagList, ";");
						mp.setTags(tags);
						matchProductMapper.insert(mp);
					}
				}
			}
		}
	}
	}
}
