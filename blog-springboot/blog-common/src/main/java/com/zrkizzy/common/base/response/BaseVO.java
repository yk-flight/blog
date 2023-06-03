package com.zrkizzy.common.base.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基本数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/3/7
 */
@Data
public class BaseVO implements Serializable {

    /**
     * 主键（雪花算法）
     */
    @JsonFormat(shape =JsonFormat.Shape.STRING)
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
