package com.ark.hngxt.product.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ark.dependencies.common.auth.JWTManager;
import com.ark.dependencies.common.auth.annotation.AuthorityConfig;
import com.ark.dependencies.common.auth.interceptor.RestTemplateUserContextInterceptor;
import com.ark.dependencies.common.auth.interceptor.UserContextInterceptor;
import com.ark.dependencies.common.auth.util.AuthSettings;

@Configuration
public class AuthConfig implements WebMvcConfigurer {
	
	@Bean
	public AuthSettings authSettings()
	{
		return new AuthSettings();
	}
	
	@Bean
	public JWTManager jwtManager()
	{
		return new JWTManager(authSettings());
	}
	
	/**
	 * 请求拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UserContextInterceptor(authSettings(), jwtManager()));
	}
	
	
	/***
	 * RestTemplate 拦截器，在发送请求前设置鉴权的用户上下文信息
	 * @return
	 */
	@LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new RestTemplateUserContextInterceptor(authSettings().getTokenName()));
        return restTemplate;
    }
	
	/**
	 * 开启注解权限控制
	 * @return
	 */
	@Bean
	public AuthorityConfig authorityConfig()
	{
		return new AuthorityConfig();
	}
}
