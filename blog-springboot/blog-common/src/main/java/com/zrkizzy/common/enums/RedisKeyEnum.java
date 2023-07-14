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
    USER("user:", "用户信息"),
    /**
     * 验证码信息前缀
     */
    CAPTCHA("captcha:", "验证码");

    /**
     * 缓存键
     */
    private final String key;

    /**
     * 备注
     */
    private final String remark;

}
