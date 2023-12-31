package com.zrkizzy.common.constant;

/**
 * Redis全局键值常量
 *
 * @author zhangrongkang
 * @since 2023/3/9
 */
public class RedisConst {

    /**
     * token前缀
     */
    private static final String TOKEN_PREFIX = "token:";

    /**
     * 用户前缀
     */
    public static final String USER_PREFIX = "user:";

    /**
     * 登录验证码前缀
     */
    public static final String CAPTCHA_PREFIX = "captcha:";

    /**
     * 邮箱验证码前缀
     */
    public static final String CAPTCHA_EMAIL_PREFIX = "captcha:email:";

    /**
     * 手机短信验证码前缀
     */
    public static final String CAPTCHA_SMS_PREFIX = "captcha:sms:";

    /**
     * 系统基本配置键
     */
    public static final String CONFIG_BASIC_KEY = "config:basic";

}
