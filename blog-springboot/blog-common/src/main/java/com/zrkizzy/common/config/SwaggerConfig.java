package com.zrkizzy.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger配置类
 *
 * @author zhangrongkang
 * @since 2023/3/6
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 包扫描的位置
                .apis(RequestHandlerSelectors.basePackage("com.zrkizzy.web.controller"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContext())
                .securitySchemes(securitySchemes());
    }

    /**
     * Swagger文档接口参数信息
     *
     * @return 文档接口参数信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("个人博客接口文档")
                .description("个人博客API文档")
                .version("1.0.0")
                .contact(new Contact("世纪末的架构师", "https://www.zrkizzy.com", "1072876976@qq.com"))
                .build();
    }

    private List<SecurityScheme> securitySchemes() {
        // 设置请求头信息
        List<SecurityScheme> result = new ArrayList<>();
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", "Header");
        result.add(apiKey);
        return result;
    }

    /**
     * 管理员和用户安全上下文
     *
     * @return 安全策略上下文
     */
    private List<SecurityContext> securityContext() {
        List<SecurityContext> list = new ArrayList<>();
        // 管理员安全策略上下文
        list.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                // 自动注入全局Token的路径
                .forPaths(PathSelectors.ant("/**"))
                .build());
        return list;
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        // 设置授权范围，全局
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        // 需要使用数组来将默认的授权范围进行存储
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        // 第二个参数就是数组
        result.add(new SecurityReference("Authorization", authorizationScopes));
        return result;
    }
}