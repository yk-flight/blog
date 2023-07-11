package com.zrkizzy.server.service.monitor.impl;

import com.zrkizzy.common.base.response.PageResult;
import com.zrkizzy.common.constant.RedisConst;
import com.zrkizzy.common.service.IRedisService;
import com.zrkizzy.common.utils.bean.BeanCopyUtil;
import com.zrkizzy.data.domain.User;
import com.zrkizzy.data.query.monitor.OnlineQuery;
import com.zrkizzy.data.vo.monitor.OnlineUserVO;
import com.zrkizzy.server.service.monitor.IOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public PageResult<OnlineUserVO> listOnlineUsers(OnlineQuery onlineQuery) {
        // 定义返回对象
        PageResult<OnlineUserVO> result = new PageResult<>();
        // 根据在线用户关键字前缀获取所有在线用户
        Set<String> keys = redisService.scanKeys(RedisConst.USER_PREFIX + "*");
        // 在线用户总数
        long total = keys.size();
        // 当前页面用户总数
        List<OnlineUserVO> list = new ArrayList<>();
        // 拿到所有用户对应关键字
        for (String key : keys) {
            // 根据键获取到对应用户，单独处理
            User user = redisService.get(key, User.class);
            // 判断条件
            // 添加当前符合条件的用户到集合中
            list.add(BeanCopyUtil.copy(user, OnlineUserVO.class));
        }
        // 单独处理每一个用户
        // 筛选出所有符合用户条件的集合
        // 根据用户集合进行分页
        result.setList(list);
        result.setTotal(total);
        return result;
    }
}
