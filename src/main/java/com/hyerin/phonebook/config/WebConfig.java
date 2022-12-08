package com.hyerin.phonebook.config;

import com.hyerin.phonebook.interceptor.LogInterceptor;
import com.hyerin.phonebook.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 인증 Interceptor 설정
    @Override
    public void addInterceptors(InterceptorRegistry registry){

        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/members/add", "/login", "/logout", "/css/**", "/*.ico", "/error");
    }

}
