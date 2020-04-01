package com.yinuo.manage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * 跨域配置
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer{

    //获取配置文件的路径
   /* @Value("${image.location.path}")
    private String resourceDir;*/
	
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); //是否支持 cookie 跨域
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setMaxAge(300l); //缓存时间。在这个时间段内，相同的跨域请求将不再检查

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    
    
    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String url = resourceDir;
        registry.addResourceHandler("/file/**").addResourceLocations("file:"+url);
    }*/
    
}
