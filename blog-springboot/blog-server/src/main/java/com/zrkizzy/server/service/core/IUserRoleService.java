package com.zrkizzy.server.service.core;

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

}
