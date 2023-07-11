package com.zrkizzy.server.service.monitor.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.constant.RedisConst;
import com.zrkizzy.common.service.IRedisService;
import com.zrkizzy.data.domain.User;
import com.zrkizzy.data.query.monitor.OnlineQuery;
import com.zrkizzy.server.service.monitor.IOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 在线用户业务逻辑接口实现类
 *
 * @author zhangrongkang
 * @since 2023/7/11
 */
@Service
public class OnlineServiceImpl implements IOnlineService {
    @Autowired
    private IRedisService redisService;

    /**
     * 分页获取所有在线用户
     *
     * @param onlineQuery 在线用户信息查询对象
     * @return 在线用户分页对象
     */
    @Override
    public Page<User> listOnlineUsers(OnlineQuery onlineQuery) {
        // 根据在线用户关键字前缀获取所有在线用户
        Set<String> keys = redisService.scanKeys(RedisConst.USER_PREFIX + "*");
        // 拿到所有用户对应关键字
        System.out.println("开始返回数据：");
        for (String key : keys) {
            System.out.println("返回的数据： " + key);
        }
        // 单独处理每一个用户
        // 筛选出所有符合用户条件的集合
        // 根据用户集合进行分页
        return null;
    }
}
