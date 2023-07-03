package com.zrkizzy.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
    SWAGGER_RESOURCE("/swagger-resources"),
    /**
     * Swagger中API结构和操作文件
     */
    SWAGGER_API("/v2/api-docs"),
    /**
     * Swagger中的静态资源
     */
    SWAGGER_WEBJAR("/webjars"),
    /**
     * 用户登录操作
     */
    LOGIN("/login/**"),
    /**
     * 退出登录
     */
    LOGOUT("/logout"),
    /**
     * 服务器内部错误
     */
    ERROR("/error");

    /**
     * 请求路径
     */
    public final String url;

    /**
     * 判断当前请求是否为白名单的路径
     *
     * @param url 当前请求的url
     * @return true：可放行路径，false：不可放行路径
     */
    public static Boolean isAccessUrl(String url) {
        for (WhiteListEnum value : WhiteListEnum.values()) {
            if (value.getUrl().equals(url) || url.startsWith(value.getUrl())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取系统白名单放行路径
     *
     * @return 白名单路径
     */
    public static String getAccessUrl() {
        StringBuilder access = new StringBuilder();
        for (WhiteListEnum value : WhiteListEnum.values()) {
            access.append(value.getUrl()).append(",");
        }
        // 去除最后的，
        access.deleteCharAt(access.length() - 1);
        return access.toString();
    }

}
