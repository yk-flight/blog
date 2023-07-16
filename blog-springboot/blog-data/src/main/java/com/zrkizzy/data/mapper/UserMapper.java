package com.zrkizzy.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.data.domain.User;
import com.zrkizzy.common.base.response.OptionsVO;
import com.zrkizzy.data.query.UserQuery;
import com.zrkizzy.data.vo.UserVO;
import org.apache.ibatis.annotations.Param;

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
     * @param page 分页对象
     * @param userQuery 用户信息查询对象
     * @return 所有用户集合
     */
    Page<UserVO> listUsers(@Param("page") Page<User> page, @Param("userQuery") UserQuery userQuery);

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

    /**
     * 更新用户头像
     *
     * @param userId 用户ID
     * @param src 头像路径
     * @return 受影响的行数
     */
    Integer updateUserAvatar(@Param("userId") Long userId, @Param("src") String src);

    /**
     * 获取用户选项集合
     *
     * @return 获取用户选项集合
     */
    List<OptionsVO> listUserOptions();
}
