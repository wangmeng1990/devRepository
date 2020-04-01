package com.yinuo.bigdata.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.yinuo.api.user.UserApi;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author ：jias
 * @date ：2020/1/7 15:59
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.yinuo.bigdata.system.mapper")
@EnableFeignClients(clients = {UserApi.class})
public class BigDataSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BigDataSystemApplication.class,args);
    }
}
