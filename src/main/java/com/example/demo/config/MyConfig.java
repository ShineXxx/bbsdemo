package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyConfig extends WebMvcConfigurerAdapter{
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/jumppost").setViewName("post");
        registry.addViewController("/MainPage").setViewName("MainPage");
        registry.addViewController("/userinfo").setViewName("userinfo");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> list=new ArrayList<>();
        list.add("/");
        list.add("/login");
        list.add("/confirm");
//        list.add("**.html");
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/*").excludePathPatterns(list);
    }
}
