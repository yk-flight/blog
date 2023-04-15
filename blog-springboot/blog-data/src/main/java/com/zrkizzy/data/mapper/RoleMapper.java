package com.zrkizzy.data.mapper;

import com.zrkizzy.data.domain.Role;

import java.util.List;

/**
 * 角色数据持久化接口
 * 
 * @author zhangrongkang
 * @since 2023/3/8
 */
public interface RoleMapper {

    /**
     * 获取所有角色
     *
     * @return 所有角色集合
     */
    List<Role> listRoles();

    /**
     * 通过角色名称获取角色
     *
     * @param name 角色名称
     * @return 角色对象
     */
    Role getRoleByName(String name);

    /**
     * 添加新角色
     *
     * @param role 角色对象
     * @return 受影响的行数
     */
    Integer insertRole(Role role);

    /**
     * 更新角色
     *
     * @param role 角色对象
     * @return 受影响对行数
     */
    Integer updateRoleById(Role role);
}
