package com.zrkizzy.data.vo.monitor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 缓存信息数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/7/14
 */
@Data
@Builder
@AllArgsConstructor
public class CacheInfoVO implements Serializable {

    /**
     * 缓存名称
     */
    private String cacheName;

    /**
     * 缓存键
     */
    private String cacheKey;

    /**
     * 缓存值
     */
    private Object cacheValue;

}
