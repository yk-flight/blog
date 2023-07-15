package com.zrkizzy.web.controller.monitor;

import com.zrkizzy.common.annotation.OperateLogAnnotation;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.constant.AnnotationConst;
import com.zrkizzy.common.enums.RedisKeyEnum;
import com.zrkizzy.common.service.IRedisService;
import com.zrkizzy.data.vo.monitor.CacheInfoVO;
import com.zrkizzy.data.vo.monitor.CacheKeyVO;
import com.zrkizzy.data.vo.monitor.CacheTypeVO;
import com.zrkizzy.server.service.monitor.ICacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 缓存管理控制器
 *
 * @author zhangrongkang
 * @since 2023/7/13
 */
@Api(tags = "缓存管理控制器")
@RestController
@RequestMapping("/admin/cache")
public class CacheController {
    @Autowired
    private IRedisService redisService;
    @Autowired
    private ICacheService cacheService;

    @ApiOperation(value = "获取所有缓存键类型")
    @GetMapping("/listCacheType")
    public Result<List<CacheTypeVO>> listRedisKeyType() {
        // 定义返回结果
        List<CacheTypeVO> result = new ArrayList<>();
        // 遍历所有Redis类型
        for (RedisKeyEnum redisKey : RedisKeyEnum.values()) {
            // 缓存分类名称
            CacheTypeVO redisKeyTypeVO = CacheTypeVO.builder()
                    // 缓存名称
                    .name(redisKey.getKey())
                    // 备注
                    .remark(redisKey.getRemark()).build();
            // 添加当前Redis缓存键
            result.add(redisKeyTypeVO);
        }
        return Result.success(result);
    }

    @ApiOperation("获取所有缓存键")
    @GetMapping("/listCacheKeys/{type}")
    public Result<List<CacheKeyVO>> listCacheKeys(@PathVariable String type) {
        return Result.success(cacheService.listCacheKeys(type));
    }

    @ApiOperation("获取指定缓存")
    @GetMapping("/getCacheInfoByKey/{key}")
    public Result<CacheInfoVO> getCacheInfoByKey(@PathVariable String key) {
        return Result.success(cacheService.getCacheInfoByKey(key));
    }

    @ApiOperation("清理缓存列表")
    @OperateLogAnnotation(type = AnnotationConst.DELETE)
    @DeleteMapping("/clearCacheKeys/{type}")
    public Result<?> clearCacheKeys(@PathVariable String type) {
        cacheService.clearCacheKeys(type);
        return Result.success();
    }

    @ApiOperation("删除指定缓存")
    @OperateLogAnnotation(type = AnnotationConst.DELETE)
    @DeleteMapping("/deleteCacheKey/{key}")
    public Result<?> deleteCacheKey(@PathVariable String key) {
        redisService.del(key);
        return Result.success();
    }

}
