package com.zrkizzy.server.service.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.data.domain.Role;
import com.zrkizzy.data.dto.RoleDTO;
import com.zrkizzy.data.query.RoleQuery;

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
     * @param roleQuery 角色信息查询对象
     * @return 角色分页数据
     */
    Page<Role> listRoles(RoleQuery roleQuery);

    /**
     * 添加或更新角色
     *
     * @param roleDTO 角色数据接收对象
     * @return 前端响应对象
     */
    Result<?> saveRole(RoleDTO roleDTO);

    /**
     * 获取指定角色信息
     *
     * @param roleId 角色ID
     * @return 角色对象
     */
    Role getRoleById(Long roleId);

    /**
     * 批量删除角色数据
     *
     * @param ids 角色ID
     * @return true：删除成功，false：删除失败
     */
    Boolean deleteBatch(List<Long> ids);

    /**
     * 获取默认角色ID
     *
     * @return 默认角色ID
     */
    Long getDefaultRoleId();

}
