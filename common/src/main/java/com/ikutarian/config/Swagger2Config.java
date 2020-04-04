package com.ikutarian.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger相关的配置
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    // 文档的访问地址 http://hostname:port/swagger-ui.html 或者 http://hostname:port/doc.html
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)  // 文档的类型
                .apiInfo(new ApiInfoBuilder()
                        .title("天天吃货电商平台API")
                        .contact(new Contact("ikutarian", "https://www.ikutarian.com", "ikutarian9527@gmail.com"))
                        .description("专门为天天吃货的提供的API文档").version("1.0.1")
                        .termsOfServiceUrl("https://www.ikutarian.com").build())  // 文档描述信息
                .select().apis(RequestHandlerSelectors.basePackage("com.ikutarian.controller")) // 指定controller所在的package
                .paths(PathSelectors.any())  // 所有的controller
                .build();
    }
}
