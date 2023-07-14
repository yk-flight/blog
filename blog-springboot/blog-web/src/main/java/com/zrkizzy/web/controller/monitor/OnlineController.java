package com.zrkizzy.web.controller.monitor;

import com.zrkizzy.common.annotation.OperateLogAnnotation;
import com.zrkizzy.common.base.response.PageResult;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.constant.AnnotationConst;
import com.zrkizzy.common.service.IRedisService;
import com.zrkizzy.data.query.monitor.OnlineQuery;
import com.zrkizzy.data.vo.monitor.OnlineUserVO;
import com.zrkizzy.server.service.monitor.IOnlineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.zrkizzy.common.constant.RedisConst.USER_PREFIX;

/**
 * 在线用户控制器
 *
 * @author zhangrongkang
 * @since 2023/7/11
 */
@Api(tags = "在线用户控制器")
@RestController
@RequestMapping("/admin/online")
public class OnlineController {

    @Autowired
    private IRedisService redisService;
    @Autowired
    private IOnlineService onlineService;

    @ApiOperation("分页获取在线用户")
    @PostMapping("/list")
    public Result<PageResult<OnlineUserVO>> listOnline(@RequestBody OnlineQuery onlineQuery) {
        return Result.success(onlineService.listOnlineUsers(onlineQuery));
    }

    @ApiOperation("下线指定用户")
    @OperateLogAnnotation(type = AnnotationConst.DELETE)
    @DeleteMapping("/offline/{track}")
    public Result<?> offlineUser(@PathVariable String track) {
        // 下线指定用户
        redisService.del(USER_PREFIX + track);
        return Result.success();
    }
}
