package com.zrkizzy.server.service.monitor;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.data.domain.User;
import com.zrkizzy.data.query.monitor.OnlineQuery;

/**
 * 在线用户业务逻辑接口
 *
 * @author zhangrongkang
 * @since 2023/7/11
 */
public interface IOnlineService {

    /**
     * 分页获取所有在线用户
     *
     * @param onlineQuery 在线用户信息查询对象
     * @return 在线用户分页对象
     */
    Page<User> listOnlineUsers(OnlineQuery onlineQuery);
}
