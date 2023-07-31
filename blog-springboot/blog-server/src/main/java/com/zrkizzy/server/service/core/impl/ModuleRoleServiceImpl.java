package com.zrkizzy.server.service.core.impl;

import com.zrkizzy.data.dto.core.ModuleRoleDTO;
import com.zrkizzy.data.mapper.ModuleRoleMapper;
import com.zrkizzy.server.service.core.IModuleRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 模块角色业务逻辑接口实现类
 *
 * @author zhangrongkang
 * @since 2023/7/31
 */
@Service
public class ModuleRoleServiceImpl implements IModuleRoleService {

    @Autowired
    private ModuleRoleMapper moduleRoleMapper;

    /**
     * 分配角色模块权限
     *
     * @param moduleRoleDTO 模块角色关联数据传输对象
     * @return 是否分配成功
     */
    @Override
    public Boolean save(ModuleRoleDTO moduleRoleDTO) {
        // 角色ID
        Long roleId = moduleRoleDTO.getRoleId();
        // 先根据角色ID删除对应资源权限
        moduleRoleMapper.deleteByRoleId(roleId);
        // 模块ID
        List<Long> moduleIds = moduleRoleDTO.getModuleId();
        // 先将当前角色具有的所有模块权限删除
        return null;
    }

    /**
     * 根据角色ID获取角色模块权限
     *
     * @param roleId 角色ID
     * @return 所有选中的模块ID集合
     */
    @Override
    public List<Long> listModuleIdByRoleId(Long roleId) {
        // 获取角色模块数据并返回结果
        return moduleRoleMapper.listModuleIdByRoleId(roleId);
    }
}
