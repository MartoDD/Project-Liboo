package com.example.projectliboo.config;

import com.example.projectliboo.util.DdosInterceptor;
import com.example.projectliboo.util.TimingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {



    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new DdosInterceptor());

        registry.addInterceptor(new TimingInterceptor());
    }

}
