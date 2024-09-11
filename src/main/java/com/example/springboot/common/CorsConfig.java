package com.example.springboot.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/*全局的跨域资源共享（CORS）配置类*/
@Configuration
public class CorsConfig {

    private CorsConfiguration buildConfig() {//用于定义跨域请求的具体规则
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1容许任何域名使用
        corsConfiguration.addAllowedHeader("*"); // 2容许任何头
        corsConfiguration.addAllowedMethod("*"); // 3容许任何方法（post、get等）
        return corsConfiguration;
    }

    /*创建 CORS 过滤器*/
    @Bean //将 corsFilter() 方法返回的对象注册为 Spring 的 Bean，表明这是一个需要由 Spring 管理的组件。
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4
        return new CorsFilter(source);
    }
}