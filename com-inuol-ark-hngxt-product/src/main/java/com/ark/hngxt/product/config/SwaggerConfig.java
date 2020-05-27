package com.ark.hngxt.product.config;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ark.dependencies.common.auth.util.AuthSettings;
import com.ark.dependencies.common.util.SwaggerSettings;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	@Value("${spring.cloud.config.profile}")
	private String optModel;
	private final String DEV="dev";
	
	@Bean
    public Docket createRestApi(AuthSettings authSettings) {
    	
    	//添加head参数配置start
        ParameterBuilder tokenPar1 = new ParameterBuilder();
        //tokenPar1.name(authSettings.getTokenIdHeaderName()).description("经过网关访问时，只提供TOKEN-ID").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        
        ParameterBuilder tokenPar2 = new ParameterBuilder();
        
        List<Parameter> pars = new ArrayList<>();
        
        if(optModel!=null&&optModel.contentEquals(DEV))
        {
        	tokenPar1.name(authSettings.getTokenUserName()).description("通过网关访问时请输入此Token(x-auth-token)，不经过网关访问，将不能获取当前用户信息！").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        	tokenPar2.name(authSettings.getTokenName()).description("单独本机开发时,不经过关调试,可在此手动输入Token(x-auth-token)，否则不能身份验证，也不能获取当前用户信息！").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        	
        	pars.add(tokenPar1.build());
        	pars.add(tokenPar2.build());
        }
        else
        {
        	tokenPar2.name(authSettings.getTokenUserName()).description("通过网关访问时请输入此Token(x-auth-token)，不经过网关访问，将不能获取当前用户信息！").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        	
        	pars.add(tokenPar2.build());
        }
        
        //List<Parameter> pars = new ArrayList<>();
        //pars.add(tokenPar1.build());
        //pars.add(tokenPar2.build());
        //添加head参数配置end
        
        //.tags(new Tag("LH-SysUserRepository", "Repository for Address entities"))
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                 //注意这里
                .globalOperationParameters(pars);
    }
	
	@Bean
	public SwaggerSettings swaggerSettings()
	{
		return new SwaggerSettings();
	}
	
	@Autowired
	private SwaggerSettings swaggerSettings;

    private ApiInfo apiInfo() {
    	 return new ApiInfoBuilder()
                 .title(swaggerSettings.getTitle())
                 .description(swaggerSettings.getDescription())
                 .termsOfServiceUrl(swaggerSettings.getTermsOfServiceUrl())
                 .contact(new Contact(swaggerSettings.getContactName(), swaggerSettings.getContactUrl(), swaggerSettings.getContactEmail()))
                 .version(swaggerSettings.getVersion())
                 .build();
    }

}
