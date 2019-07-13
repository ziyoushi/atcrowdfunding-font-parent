package com.atguigu.atcrowdfunding.atcrowdfunding.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 开启springcloud相关的配置
 * 开启swagger2 否则访问 http://localhost:8100/swagger-ui.html 会进不去
 */
@EnableFeignClients
@EnableCircuitBreaker
@EnableDiscoveryClient
@MapperScan("com.atguigu.atcrowdfunding.atcrowdfunding.user.mapper")
@EnableSwagger2
@SpringBootApplication
public class AtcrowdfundingUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtcrowdfundingUserApplication.class, args);
    }

}
