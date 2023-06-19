package com.zrkizzy.generator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表格列数据实体类
 *
 * @author zhangrongkang
 * @since 2023/6/8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Field {

    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 字段类型
     */
    private String columnType;

    /**
     * 字段注释
     */
    private String comment;

    /**
     * 字段是否可以为空
     */
    private Boolean isNull;

    /**
     * 对应Java类型
     */
    private String javaType;

    /**
     * 对应Java名称
     */
    private String javaName;

    /**
     * Java驼峰后的名称
     */
    private String javaUpName;

}
