package com.zrkizzy.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

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
     * 返回Redis缓存键枚举类集合
     *
     * @return 所有Redis缓存键
     */
    public static List<RedisKeyEnum> listRedisKey() {
        return Arrays.asList(RedisKeyEnum.values());
    }
}
