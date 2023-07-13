package com.zrkizzy.web.controller.monitor;

import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.enums.RedisKeyEnum;
import com.zrkizzy.data.vo.monitor.CacheTypeVO;
import com.zrkizzy.server.service.monitor.ICacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private ICacheService cacheService;

    @ApiOperation(value = "获取所有Redis缓存键类型")
    @GetMapping("/listRedisKeyType")
    public Result<List<CacheTypeVO>> listRedisKeyType() {
        // 定义返回结果
        List<CacheTypeVO> result = new ArrayList<>();
        // 遍历所有Redis类型
        for (RedisKeyEnum redisKey : RedisKeyEnum.values()) {
            // 缓存分类名称
            CacheTypeVO redisKeyTypeVO = CacheTypeVO.builder().name(redisKey.getName())
                    // 缓存键
                    .key(redisKey.getKey())
                    // 备注
                    .remark(redisKey.getRemark()).build();
            // 添加当前Redis缓存键
            result.add(redisKeyTypeVO);
        }
        return Result.success(result);
    }

}
