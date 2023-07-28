package com.zrkizzy.server.service.core;

import com.zrkizzy.data.dto.UserRoleDTO;

import java.util.List;

/**
 * 用户角色业务逻辑接口
 *
 * @author zhangrongkang
 * @since 2023/3/15
 */
public interface IUserRoleService {

    /**
     * 添加用户角色关联信息
     *
     * @param userId 用户ID
     * @return 是否添加成功
     */
    Boolean setDefaultRole(Long userId);

    /**
     * 更新用户角色
     *
     * @param userRoleDTO 用户角色关联数据传输对象
     * @return 是否更新成功
     */
    Boolean update(UserRoleDTO userRoleDTO);

    /**
     * 通过用户ID获取角色ID
     *
     * @param ids 用户ID集合
     * @return 角色ID集合
     */
    List<Long> listRoleIdByUserId(List<Long> ids);
}
