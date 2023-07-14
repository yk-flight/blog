package com.zrkizzy.data.vo.monitor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 缓存键类型数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/7/13
 */
@Data
@Builder
@AllArgsConstructor
public class CacheTypeVO implements Serializable {

    /**
     * 缓存名称
     */
    private final String name;

    /**
     * 备注
     */
    private final String remark;

}
