package com.sample.app.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sample.app.interceptors.PerformanceReportInterceptor;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer{
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new PerformanceReportInterceptor()).addPathPatterns("/*");
    }
}