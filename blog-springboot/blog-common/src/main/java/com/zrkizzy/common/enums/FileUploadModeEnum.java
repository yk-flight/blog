package com.zrkizzy.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文件上传模式枚举
 *
 * @author zhangrongkang
 * @since 2023/5/23
 */
@Getter
@AllArgsConstructor
public enum FileUploadModeEnum {

    /**
     * 阿里云OSS
     */
    ALIYUN_OSS("OSS对象存储", "oss"),
    /**
     * 本地模式
     */
    LOCAL_UPLOAD("本地上传", "local");

    /**
     * 上传方式名称
     */
    private final String mode;

    /**
     * 上传方式标识
     */
    private final String mark;

}
