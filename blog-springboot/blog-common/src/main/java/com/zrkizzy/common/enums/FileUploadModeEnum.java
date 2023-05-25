package com.zrkizzy.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

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
    ALIYUN_OSS("阿里云OSS", "aliyun"),
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

    /**
     * 返回文件上传枚举类集合
     *
     * @return 所有上传枚举类型集合
     */
    public static List<FileUploadModeEnum> listFileUploadMode() {
        return Arrays.asList(FileUploadModeEnum.values());
    }

}
