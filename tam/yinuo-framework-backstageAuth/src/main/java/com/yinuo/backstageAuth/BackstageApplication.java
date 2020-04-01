package com.yinuo.backstageAuth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient //一个EurekaClient从EurekaServer发现服务
@EntityScan("com.inuol.entity") //扫描实体类
@MapperScan("com.yinuo.backstageAuth.mapper")
public class BackstageApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackstageApplication.class,args);
    }

}
