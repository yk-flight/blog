package com.zrkizzy.server.service.core.impl;

import com.zrkizzy.data.mapper.UserRoleMapper;
import com.zrkizzy.server.service.core.IUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 用户角色业务逻辑接口实现类
 *
 * @author zhangrongkang
 * @since 2023/3/15
 */
@Slf4j
@Service
public class UserRoleServiceImpl implements IUserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

}
