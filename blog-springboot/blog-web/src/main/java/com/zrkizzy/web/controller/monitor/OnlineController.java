package com.zrkizzy.web.controller.monitor;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.data.domain.User;
import com.zrkizzy.data.query.monitor.OnlineQuery;
import com.zrkizzy.server.service.monitor.IOnlineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    private IOnlineService onlineService;

    @ApiOperation("分页获取在线用户")
    @PostMapping("/list")
    public Result<?> listOnline(@RequestBody OnlineQuery onlineQuery) {
        Page<User> list = onlineService.listOnlineUsers(onlineQuery);
        return Result.success();
    }
}
