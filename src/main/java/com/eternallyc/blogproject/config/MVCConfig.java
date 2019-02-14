package com.eternallyc.blogproject.config;


import com.eternallyc.blogproject.interceptors.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class MVCConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/blogs/classification/admin/**")
        .addPathPatterns("/blogs/article/admin/**");
        //这个链接来的请求进行拦截
    }
}
