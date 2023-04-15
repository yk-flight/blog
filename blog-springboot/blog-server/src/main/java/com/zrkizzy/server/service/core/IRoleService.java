package com.zrkizzy.server.service.core;

import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.data.domain.Role;
import com.zrkizzy.data.dto.RoleDTO;

import java.util.List;

/**
 * 角色业务逻辑接口
 *
 * @author zhangrongkang
 * @since 2023/3/8
 */
public interface IRoleService {
    /**
     * 获取所有角色
     *
     * @return 所有角色集合
     */
    List<Role> listRoles();

    /**
     * 添加或更新角色
     *
     * @param roleDTO 角色数据接收对象
     * @return 前端响应对象
     */
    Result<?> saveRole(RoleDTO roleDTO);
}
