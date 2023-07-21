package com.zrkizzy.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 网站配置枚举
 *
 * @author zhangrongkang
 * @since 2023/7/21
 */
@Getter
@AllArgsConstructor
public enum ConfigTypeEnum {

    /**
     * 图片
     */
    IMAGE,
    /**
     * 文本
     */
    TEXT,
    /**
     * 单选
     */
    RADIO

}
