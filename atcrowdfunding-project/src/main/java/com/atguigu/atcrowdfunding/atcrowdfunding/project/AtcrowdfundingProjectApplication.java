package com.atguigu.atcrowdfunding.atcrowdfunding.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
//开启事务管理 才可以使用@Transactional
@EnableTransactionManagement
@MapperScan("com.atguigu.atcrowdfunding.atcrowdfunding.project.mapper")
public class AtcrowdfundingProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtcrowdfundingProjectApplication.class, args);
    }

}
