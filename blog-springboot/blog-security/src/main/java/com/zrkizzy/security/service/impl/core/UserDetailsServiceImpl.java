package com.zrkizzy.security.service.impl.core;

import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.service.IRedisService;
import com.zrkizzy.data.domain.User;
import com.zrkizzy.security.util.UserDetailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.zrkizzy.common.constant.RedisConst.USER_PREFIX;

/**
 * 用户加载业务逻辑
 *
 * @author zhangrongkang
 * @since 2023/3/10
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDetailUtil userDetailUtil;

    @Autowired
    private IRedisService redisService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.info("------------------ 开始查询User对象 ------------------");
        // 查看Redis中是否存在当前用户
        User user = redisService.get(USER_PREFIX + username, User.class);
        // 2. 如果Redis中不存在当前用户信息则从数据库获取
        if (null == user) {
            // 抛出未找到用户异常
            throw new UsernameNotFoundException(HttpStatusEnum.USER_NOT_FOUND.getMessage());
        }
//        log.info("------------------- 结束查询User对象 -------------------");
        // 将获取到的用户对象转为UserDetails对象返回
        return userDetailUtil.convertUserDetails(user);
    }

}
