package com.zrkizzy.server.service.core;

import com.zrkizzy.common.base.response.OptionsVO;
import com.zrkizzy.common.base.response.PageResult;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.data.dto.AvatarDTO;
import com.zrkizzy.data.dto.LoginDTO;
import com.zrkizzy.data.dto.PasswordDTO;
import com.zrkizzy.data.dto.UserInfoDTO;
import com.zrkizzy.data.query.UserQuery;
import com.zrkizzy.data.vo.UserInfoVO;
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
     * @return 公共返回对象
     */
    Result<String> login(LoginDTO loginDTO);

    /**
     * 获取登录用户个人信息
     *
     * @return 个人信息数据返回对象
     */
    Result<UserInfoVO> getUserInfo();

    /**
     * 更新用户个人信息
     *
     * @param userInfoDTO 用户个人信息数据传输对象
     * @return 公共返回对象
     */
    Result<?> updateUserInfo(UserInfoDTO userInfoDTO);

    /**
     * 更新用户密码
     *
     * @param passwordDTO 用户更新密码数据传递对象
     * @return 公共返回对象
     */
    Result<?> updatePassword(PasswordDTO passwordDTO);

    /**
     * 更新登录用户头像
     *
     * @param avatarDTO 用户头像数据传输对象
     * @return 用户头像访问路径
     */
    Result<String> updateLoginUserAvatar(AvatarDTO avatarDTO);

    /**
     * 获取用户选项集合
     *
     * @return 获取用户选项集合
     */
    List<OptionsVO> listUserOptions();
}
