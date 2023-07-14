package com.zrkizzy.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Redis缓存键枚举
 *
 * @author zhangrongkang
 * @since 2023/7/13
 */
@Getter
@AllArgsConstructor
public enum RedisKeyEnum {

    /**
     * 用户信息前缀
     */
    USER("user_info", "user:", "用户信息"),
    /**
     * 验证码信息前缀
     */
    CAPTCHA("captcha_info", "captcha:", "验证码");

    /**
     * 缓存分类名称
     */
    private final String name;

    /**
     * 缓存键
     */
    private final String key;

    /**
     * 备注
     */
    private final String remark;

    /**
     * 根据缓存键获取缓存名称
     *
     * @param key 缓存键
     * @return 缓存名称
     */
    public static String getCacheNameByKey(String key) {
        for (RedisKeyEnum value : RedisKeyEnum.values()) {
            if (value.getKey().equals(key.substring(0, key.indexOf(":") + 1))) {
                return value.getName();
            }
        }
        return null;
    }

}
