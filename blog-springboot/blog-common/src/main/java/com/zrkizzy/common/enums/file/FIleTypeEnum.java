package com.zrkizzy.common.enums.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Excel文件枚举类
 *
 * @author zhangrongkang
 * @since 2023/7/17
 */
@Getter
@AllArgsConstructor
public enum FIleTypeEnum {

    /**
     * xlsx后缀
     */
    XLSX(".xlsx");

    /**
     * Excel文件枚举
     */
    private final String type;

}
