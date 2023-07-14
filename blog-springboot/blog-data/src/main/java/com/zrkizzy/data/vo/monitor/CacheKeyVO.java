package com.zrkizzy.data.vo.monitor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 缓存键数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/7/14
 */
@Data
@Builder
@AllArgsConstructor
public class CacheKeyVO implements Serializable {

    /**
     * 缓存键
     */
    private String key;


}
