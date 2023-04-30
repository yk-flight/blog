package com.zrkizzy.server.service.core;

import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.data.domain.User;
import com.zrkizzy.data.dto.LoginDTO;
import com.zrkizzy.server.vo.UserInfoVO;

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
     * @return 所有用户集合
     */
    List<User> listUsers();

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
}
