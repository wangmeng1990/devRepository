package com.yinuo.bigdata.system.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;

@Configuration
public class MyBatisConfig {
	
	    @Bean
	    public PageHelper pageHelper(){
	        PageHelper pageHelper = new PageHelper();
	        Properties p = new Properties();
	        p.setProperty("offsetAsPageNum","true");
	        p.setProperty("rowBoundsWithCount","true");
	        p.setProperty("reasonable","true");
	        pageHelper.setProperties(p);
	        return pageHelper;
	    }


}
