package com.zrkizzy.common.base;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基本实体类
 *
 * @author zhangrongkang
 * @since 2023/3/7
 */
@Data
public class BaseEntity implements Serializable {

    /**
     * 主键（雪花算法）
     */
    @TableId
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
