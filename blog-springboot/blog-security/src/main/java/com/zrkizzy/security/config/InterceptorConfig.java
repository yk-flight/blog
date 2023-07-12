package com.zrkizzy.security.config;

import com.zrkizzy.common.enums.WhiteListEnum;
import com.zrkizzy.security.core.interceptor.SecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 *
 * @author zhangrongkang
 * @since 2023/7/12
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private SecurityInterceptor securityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 启用自定义拦截器
        registry.addInterceptor(securityInterceptor).addPathPatterns("/**").excludePathPatterns(WhiteListEnum.getAccessUrl());
    }
}
