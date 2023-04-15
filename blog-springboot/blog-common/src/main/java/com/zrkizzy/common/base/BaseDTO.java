package com.zrkizzy.common.base;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基本数据传输对象
 *
 * @author zhangrongkang
 * @since 2023/3/8
 */
@Data
public class BaseDTO implements Serializable {

    /**
     * 主键（雪花算法）
     */
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
