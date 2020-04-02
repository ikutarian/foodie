package com.ikutarian.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.ikutarian.util.DateTimeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

    /**
     * 自动填充功能
     */
    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(new DateTimeHandler());
        return globalConfig;
    }
}
