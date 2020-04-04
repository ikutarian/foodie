package com.ikutarian.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域相关的配置
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration conf = new CorsConfiguration();
        // 允许的origin
        conf.addAllowedOrigin("http://localhost:8080");
        // 允许发送cookie信息
        conf.setAllowCredentials(true);
        // 允许的方法
        conf.addAllowedMethod("*");
        // 允许的header
        conf.addAllowedHeader("*");
        // 为url添加映射路径
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", conf);

        return new CorsFilter(corsSource);
    }
}
