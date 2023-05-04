package com.zrkizzy.server.service.core;

import com.zrkizzy.data.domain.UserInfo;

/**
 * 用户信息业务逻辑接口
 *
 * @author zhangrongkang
 * @since 2023/5/4
 */
public interface IUserInfoService {
    /**
     * 通过ID获取用户信息
     *
     * @param id 用户ID（同用户信息ID）
     * @return 用户信息实体
     */
    UserInfo getUserInfoById(Long id);

}
