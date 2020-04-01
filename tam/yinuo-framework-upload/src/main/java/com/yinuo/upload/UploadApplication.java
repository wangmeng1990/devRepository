/**
 * 文件上传服务*/
package com.yinuo.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;



@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient //一个EurekaClient从EurekaServer发现服务
public class UploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadApplication.class,args);
    }

}
