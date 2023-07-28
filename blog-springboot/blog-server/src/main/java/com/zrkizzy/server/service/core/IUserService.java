package com.zrkizzy.server.service.core;

import com.zrkizzy.common.base.response.OptionsVO;
import com.zrkizzy.common.base.response.PageResult;
import com.zrkizzy.data.dto.*;
import com.zrkizzy.data.query.UserQuery;
import com.zrkizzy.data.vo.UserVO;

import java.util.List;

/**
 * 用户业务逻辑接口
 *
 * @author zhangrongkang
 * @since 2023/3/7
 */
public interface IUserService {
    /**
     * 获取所有用户
     *
     * @param userQuery 用户查询对象
     * @return 所有用户分页数据
     */
    PageResult<UserVO> listUsers(UserQuery userQuery);

    /**
     * 用户登录返回Token
     *
     * @param loginDTO 用户登录数据传输对象
     * @return Token
     */
    String login(LoginDTO loginDTO);

    /**
     * 更新登录用户个人信息
     *
     * @param userUpdateDTO 用户个人信息数据传输对象
     * @return 受影响的行数
     */
    Integer updateLoginUser(UserUpdateDTO userUpdateDTO);

    /**
     * 更新用户密码
     *
     * @param passwordDTO 用户更新密码数据传递对象
     * @return 受影响的行数
     */
    Integer updatePassword(PasswordDTO passwordDTO);

    /**
     * 更新登录用户头像
     *
     * @param avatarDTO 用户头像数据传输对象
     * @return 用户头像访问路径
     */
    String updateLoginUserAvatar(AvatarDTO avatarDTO);

    /**
     * 获取用户选项集合
     *
     * @return 获取用户选项集合
     */
    List<OptionsVO> listUserOptions();

    /**
     * 通过ID获取用户返回对象
     *
     * @param id 用户ID
     * @return 用户数据返回对象
     */
    UserVO getUserById(Long id);

    /**
     * 新增用户
     *
     * @param userDTO 用户数据传输对象
     * @return 是否新增成功
     */
    Boolean insert(UserDTO userDTO);

    /**
     * 更新指定用户信息
     *
     * @param userUpdateDTO 用户更新数据对象
     * @return 是否跟新成功
     */
    Boolean updateUser(UserUpdateDTO userUpdateDTO);

    /**
     * 修改用户状态
     *
     * @param id 用户ID
     * @return 是否修改成功
     */
    Boolean updateUserStatus(Long id);

    /**
     * 重置用户密码
     *
     * @param id 用户ID
     * @return 是否重置成功
     */
    Boolean resetPassword(Long id);

    /**
     * 批量删除用户
     *
     * @param ids 用户集合
     * @return 是否删除成功
     */
    Boolean delete(List<Long> ids);
}
