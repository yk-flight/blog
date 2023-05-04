package com.zrkizzy.server.service.core.impl;

import com.zrkizzy.data.domain.UserInfo;
import com.zrkizzy.data.mapper.UserInfoMapper;
import com.zrkizzy.server.service.core.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户信息业务逻辑接口实现类
 *
 * @author zhangrongkang
 * @since 2023/5/4
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 通过ID获取用户信息
     *
     * @param id 用户ID（同用户信息ID）
     * @return 用户信息实体
     */
    @Override
    public UserInfo getUserInfoById(Long id) {
        return userInfoMapper.selectById(id);
    }
}
