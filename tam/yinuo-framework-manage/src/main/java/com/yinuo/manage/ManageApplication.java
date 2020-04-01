/**
 * 中台管理*/
package com.yinuo.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@SpringBootApplication
@EnableDiscoveryClient //一个EurekaClient从EurekaServer发现服务
@MapperScan("com.yinuo.manage.mapper")
@EnableTransactionManagement
public class ManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class,args);
    }

}
