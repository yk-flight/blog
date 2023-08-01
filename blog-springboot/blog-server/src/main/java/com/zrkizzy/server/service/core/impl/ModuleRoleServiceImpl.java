package com.zrkizzy.server.service.core.impl;

import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.data.domain.core.ModuleRole;
import com.zrkizzy.data.dto.core.ModuleRoleDTO;
import com.zrkizzy.data.mapper.ModuleRoleMapper;
import com.zrkizzy.security.core.filters.SecurityMetadataSourceFilter;
import com.zrkizzy.server.service.core.IModuleRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private SecurityMetadataSourceFilter securityMetadataSourceFilter;

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

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
        // 先根据角色ID删除模块权限
        moduleRoleMapper.deleteByRoleId(roleId);
        // 模块ID
        List<Long> moduleIds = moduleRoleDTO.getModuleId();
        List<ModuleRole> moduleRoles = new ArrayList<>();
        for (Long moduleId : moduleIds) {
            ModuleRole moduleRole = new ModuleRole();
            // 定义模块角色关联对象属性
            moduleRole.setId(snowFlakeUtil.nextId());
            moduleRole.setRoleId(roleId);
            moduleRole.setModuleId(moduleId);
            moduleRole.setCreateTime(LocalDateTime.now());
            // 添加模块角色关联对象
            moduleRoles.add(moduleRole);
        }
        // 添加当前模块权限
//        if (moduleRoleMapper.insertBatch(moduleRoles) == moduleIds.size()) {
//            // 清空内存中的动态权限
//            securityMetadataSourceFilter.clearDataSource();
//        }
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
