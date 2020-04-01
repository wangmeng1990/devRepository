package com.yinuo.scenic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient //一个EurekaClient从EurekaServer发现服务
@EntityScan("com.inuol.entity") //扫描实体类
@MapperScan("com.yinuo.scenic.mapper")
public class ScenicApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScenicApplication.class,args);
    }

}
