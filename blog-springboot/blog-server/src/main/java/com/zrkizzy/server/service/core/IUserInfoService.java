package com.zrkizzy.server.service.core;

import com.zrkizzy.data.domain.core.UserInfo;
import com.zrkizzy.data.vo.UserInfoVO;

/**
 * 用户信息业务逻辑接口
 *
 * @author zhangrongkang
 * @since 2023/5/4
 */
public interface IUserInfoService {

    /**
     * 获取登录用户个人信息
     *
     * @return 个人信息数据返回对象
     */
    UserInfoVO getUserInfo();

    /**
     * 通过ID获取用户信息
     *
     * @param id 用户ID（同用户信息ID）
     * @return 用户信息实体
     */
    UserInfo getUserInfoById(Long id);

}
