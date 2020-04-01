/**
 * 预约管理*/
package com.yinuo.appointment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import com.yinuo.api.user.UserApi;


@SpringBootApplication
@EnableDiscoveryClient //一个EurekaClient从EurekaServer发现服务
@EnableFeignClients(clients = {UserApi.class})
@ComponentScan(basePackages  ={"com.yinuo.api.common","com.yinuo.appointment"})
@MapperScan("com.yinuo.appointment.mapper")
public class AppointmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppointmentApplication.class,args);
    }

}
