package com.zrkizzy.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrkizzy.data.domain.User;

import java.util.List;

/**
 * 用户数据持久化接口
 *
 * @author zhangrongkang
 * @since 2023/3/7
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取所有用户
     *
     * @return 所有用户集合
     */
    List<User> listUsers();

    /**
     * 通过用户名获取用户对象
     *
     * @param username 用户名
     * @return 用户对象
     */
    User getUserByUsername(String username);

    /**
     * 通过用户ID来获取用户对象
     *
     * @param userId 用户ID
     * @return 用户对象
     */
    User getUserByUserId(Long userId);
}
