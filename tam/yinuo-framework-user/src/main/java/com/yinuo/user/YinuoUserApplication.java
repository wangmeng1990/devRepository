package com.yinuo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.yinuo.user.mapper")
public class YinuoUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(YinuoUserApplication.class);
    }
}
