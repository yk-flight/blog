package com.zrkizzy.server.service.monitor;

import com.zrkizzy.data.vo.monitor.CacheKeyVO;

import java.util.List;

/**
 * 缓存管理业务逻辑接口
 *
 * @author zhangrongkang
 * @since 2023/7/13
 */
public interface ICacheService {

    /**
     * 获取缓存键列表
     *
     * @param type 缓存类型
     * @return 缓存键列表
     */
    List<CacheKeyVO> listCacheKeys(String type);
}
