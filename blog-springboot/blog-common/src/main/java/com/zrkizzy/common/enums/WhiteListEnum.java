package com.zrkizzy.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 白名单放行枚举
 *
 * @author zhangrongkang
 * @since 2023/3/13
 */
@Getter
@AllArgsConstructor
public enum WhiteListEnum {

    /**
     * Swagger文档地址请求路径
     */
    SWAGGER_HOST("/doc.html"),
    /**
     * Swagger文档资源
     */
    SWAGGER_RESOURCE("/swagger-resources/**"),
    /**
     * Swagger中API结构和操作文件
     */
    SWAGGER_API("/v2/api-docs/**"),
    /**
     * Swagger中的静态资源
     */
    SWAGGER_WEBJAR("/webjars/**"),
    /**
     * 验证码
     */
    CAPTCHA("/captcha/**"),
    /**
     * CSS资源文件
     */
    CSS("/css/**"),
    /**
     * JS资源文件
     */
    JS("/js/**"),
    /**
     * 放行网站图标
     */
    FAVICON_ICO("/favicon.ico"),
    /**
     * 放行测试请求
     */
    TEST("/test/**"),
    /**
     * 用户登录操作
     */
    LOGIN("/login/**"),
    /**
     * 退出登录
     */
    LOGOUT("/logout");

    /**
     * 请求路径
     */
    private final String url;

    /**
     * 获取系统白名单放行路径
     *
     * @return 白名单路径
     */
    public static List<String> getAccessUrl() {
        List<String> url = new ArrayList<>();
        for (WhiteListEnum value : WhiteListEnum.values()) {
            url.add(value.getUrl());
        }
        return url;
    }

}
