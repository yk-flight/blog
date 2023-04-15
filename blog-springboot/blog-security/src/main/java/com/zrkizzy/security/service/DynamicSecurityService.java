package com.zrkizzy.security.service;

import com.zrkizzy.data.dto.ResourceRoleDTO;

import java.util.List;

/**
 * 动态权限业务接口
 *
 * @author zhangrongkang
 * @since 2023/3/13
 */
public interface DynamicSecurityService {

    /**
     * 加载资源角色数据
     *
     * @return 资源角色数据集合
     */
    List<ResourceRoleDTO> loadResourceRoleData();

}
