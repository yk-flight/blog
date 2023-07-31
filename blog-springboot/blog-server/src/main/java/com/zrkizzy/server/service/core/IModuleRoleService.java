package com.zrkizzy.server.service.core;

import com.zrkizzy.data.dto.core.ModuleRoleDTO;

import java.util.List;

/**
 * 模块角色关联业务逻辑接口
 *
 * @author zhangrongkang
 * @since 2023/7/31
 */
public interface IModuleRoleService {

    /**
     * 分配角色模块权限
     *
     * @param moduleRoleDTO 模块角色关联数据传输对象
     * @return 是否分配成功
     */
    Boolean save(ModuleRoleDTO moduleRoleDTO);

    /**
     * 根据角色ID获取角色模块权限
     *
     * @param roleId 角色ID
     * @return 所有选中的模块ID
     */
    List<Long> listModuleIdByRoleId(Long roleId);
}