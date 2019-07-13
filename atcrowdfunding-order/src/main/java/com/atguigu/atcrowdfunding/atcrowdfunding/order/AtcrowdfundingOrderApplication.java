package com.atguigu.atcrowdfunding.atcrowdfunding.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@MapperScan("com.atguigu.atcrowdfunding.atcrowdfunding.order.mapper")
public class AtcrowdfundingOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtcrowdfundingOrderApplication.class, args);
    }

}
