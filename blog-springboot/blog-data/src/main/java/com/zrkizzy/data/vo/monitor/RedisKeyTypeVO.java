package com.zrkizzy.data.vo.monitor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Redis缓存键类型数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/7/13
 */
@Data
@Builder
@AllArgsConstructor
public class RedisKeyTypeVO implements Serializable {

    /**
     * 缓存分类标识
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

}
