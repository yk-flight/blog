package com.zrkizzy.server.service.core;

import com.zrkizzy.data.dto.core.ModuleRoleDTO;

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

}
