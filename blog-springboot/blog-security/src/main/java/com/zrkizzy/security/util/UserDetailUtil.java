package com.zrkizzy.security.util;

import com.zrkizzy.data.domain.User;
import com.zrkizzy.security.entity.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 用户详情信息工具类
 *
 * @author zhangrongkang
 * @since 2023/4/13
 */
@Component
public class UserDetailUtil {
    @Autowired
    private MyUserDetails userDetails;

    /**
     * 用户对象转为用户信息对象
     *
     * @param user 用户对象
     * @return 用户详细信息对象
     */
    public UserDetails convertUserDetails(User user) {
        // 设置当前用户到用户详细信息中
        userDetails.convertUser(user);
        return userDetails;
    }
}
