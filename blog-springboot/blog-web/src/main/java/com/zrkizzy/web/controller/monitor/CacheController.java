package com.zrkizzy.web.controller.monitor;

import com.zrkizzy.server.service.monitor.ICacheService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
