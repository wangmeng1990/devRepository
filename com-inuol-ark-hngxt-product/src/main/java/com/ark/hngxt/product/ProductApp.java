package com.ark.hngxt.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.ark.dependencies.common.config.ConfigDbServerConnectionFailureError;
import com.ark.dependencies.common.config.YinuoCorsConfiguration;
import com.ark.dependencies.common.controller.ApiErrorController;
import com.ark.dependencies.common.mq.MqProducer;

/**
 * @author wangmeng
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.ark.hngxt.product.mapper")
@Import(YinuoCorsConfiguration.class)
public class ProductApp {

	public static void main(String[] args) {
		
		SpringApplication.run(ProductApp.class, args);
	}
	
	/**
	 * 配置中心连接检查
	 * @return
	 */
	@Bean 
	@ConditionalOnProperty(value = "spring.cloud.config.profile", havingValue = "test")
	ConfigDbServerConnectionFailureError configDbServerConnectionFailureError1()
	{
		return new ConfigDbServerConnectionFailureError();
	}
	/**
	 * 配置中心连接检查
	 * @return
	 */
	@Bean
	@ConditionalOnProperty(value = "spring.cloud.config.profile", havingValue = "prod")
	ConfigDbServerConnectionFailureError configDbServerConnectionFailureError2()
	{
		return new ConfigDbServerConnectionFailureError();
	}
	
	@Bean
	public ApiErrorController ApiErrorController()
	{
		return new ApiErrorController();
	}
	
	/**
	 * 产品信息推送到大数据端
	 * @param topic
	 * @return
	 */
	@Bean
	public MqProducer mqProducer(@Value("${rabbitmq.topic.ark_common}") String topic) {
		return new MqProducer(topic);
	}
}
