package com.atguigu.atcrowdfunding.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Administrator
 * @create 2019-06-20 11:16
 * 自定义配置类
 */
@Configuration
public class AppMvcConfig implements WebMvcConfigurer {

    //穿越火线
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/register.html").setViewName("register");
        registry.addViewController("/index.html").setViewName("index");

        registry.addViewController("/start-step-2.html").setViewName("protected/project/start-step-2");
        registry.addViewController("/start-step-3.html").setViewName("protected/project/start-step-3");

    }


}
