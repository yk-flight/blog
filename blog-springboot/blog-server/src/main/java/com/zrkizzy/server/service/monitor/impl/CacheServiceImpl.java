package com.zrkizzy.server.service.monitor.impl;

import com.alibaba.fastjson.JSON;
import com.zrkizzy.common.enums.RedisKeyEnum;
import com.zrkizzy.common.service.IRedisService;
import com.zrkizzy.data.vo.monitor.CacheInfoVO;
import com.zrkizzy.data.vo.monitor.CacheKeyVO;
import com.zrkizzy.server.service.monitor.ICacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 缓存管理业务逻辑接口实现类
 *
 * @author zhangrongkang
 * @since 2023/7/13
 */
@Service
public class CacheServiceImpl implements ICacheService {

    @Autowired
    private IRedisService redisService;

    /**
     * 获取缓存键列表
     *
     * @param type 缓存类型
     * @return 缓存键列表
     */
    @Override
    public List<CacheKeyVO> listCacheKeys(String type) {
        List<CacheKeyVO> result = new ArrayList<>();
        // 获取当前缓存类型对应的所有Key
        Set<String> keys = redisService.scanKeys(type + "*");
        for (String key : keys) {
            // 添加当前对象
            result.add(CacheKeyVO.builder()
                    // 进行展示的缓存键
                    .showKey(key.substring(key.indexOf(":") + 1))
                    // 缓存键
                    .key(key).build());
        }
        return result;
    }

    /**
     * 根据缓存键获取缓存信息
     *
     * @param key 缓存键
     * @return 缓存信息
     */
    @Override
    public CacheInfoVO getCacheInfoByKey(String key) {
        return CacheInfoVO.builder()
                // 缓存名称
                .cacheName(RedisKeyEnum.getCacheNameByKey(key))
                // 缓存键
                .cacheKey(key)
                // 缓存值
                .cacheValue(JSON.toJSONString(redisService.get(key))).build();
    }

}
