package com.zrkizzy.common.constant;

/**
 * 通用全局静态常量
 *
 * @author zhangrongkang
 * @since 2023/3/9
 */
public class CommonConst {

    /**
     * 正确常量
     */
    public static final Integer TRUE = 1;

    /**
     * 错误常量
     */
    public static final Integer FALSE = 0;

    /**
     * 中文编码格式
     */
    public static final String UTF_8 = "UTF-8";

    /**
     * JSON传递格式
     */
    public static final String APPLICATION_JSON = "application/json";

    /**
     * token请求头
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * Jwt负载开头
     */
    public static final String BEARER = "Bearer";

    /**
     * 图片格式
     */
    public static final String IMAGE_JPEG = "image/jpeg";

    /**
     * Http / 1.1之前版本缓存控制
     */
    public static final String PRAGMA = "Pragma";

    /**
     * 缓存控制
     */
    public static final String CACHE_CONTROL = "Cache-Control";

    /**
     * 禁止缓存
     */
    public static final String NO_CACHE = "no-cache";

    /**
     * 失效时间
     */
    public static final String EXPIRES = "Expires";

    /**
     * 布局组件
     */
    public static final String LAYOUT = "Layout";

    /**
     * 默认父ID
     */
    public static final Long PARENT_ID = 0L;

    /**
     * http请求前缀
     */
    public static final String HTTP_PREFIX = "http://";

    /**
     * https请求前缀
     */
    public static final String HTTPS_PREFIX = "https://";

    /**
     * 无需重定向
     */
    public static final String NO_REDIRECT = "noRedirect";

    /**
     * 本地登录
     */
    public static final String LOCAL_NAME = "本地登录";

    /**
     * 未知用户
     */
    public static final String UNKNOWN_USER = "未知用户";

    /**
     * 未知主机名
     */
    public static final String UNKNOWN_HOST_NAME = "未知主机名";

    /**
     * 用户解析信息
     */
    public static final String USER_AGENT = "User-Agent";

    /**
     * 本地IP地址
     */
    public static final String LOCAL_HOST = "127.0.0.1";

    /**
     * 环回地址
     */
    public static final String LOOP_BACK_HOST = "0:0:0:0:0:0:0:1";

}
