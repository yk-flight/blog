package com.zrkizzy.server.service.core.impl;

import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.data.domain.UserRole;
import com.zrkizzy.data.dto.UserRoleDTO;
import com.zrkizzy.data.mapper.UserRoleMapper;
import com.zrkizzy.server.service.core.IRoleService;
import com.zrkizzy.server.service.core.IUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    private SnowFlakeUtil snowFlakeUtil;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 添加用户角色关联信息
     *
     * @param userId 用户ID
     * @return 是否添加成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean setDefaultRole(Long userId) {
        UserRole userRole = new UserRole();
        // ID
        userRole.setId(snowFlakeUtil.nextId());
        // 用户ID
        userRole.setUserId(userId);
        // 获取默认角色ID
        userRole.setRoleId(roleService.getDefaultRoleId());
        return userRoleMapper.insert(userRole) == 1;
    }

    /**
     * 更新用户角色
     *
     * @param userRoleDTO 用户角色关联数据传输对象
     * @return 是否更新成功
     */
    @Override
    public Boolean update(UserRoleDTO userRoleDTO) {
        // 更新用户角色
        return userRoleMapper.updateByUserId(userRoleDTO.getRoleId(), userRoleDTO.getUserId()) == 1;
    }
}
