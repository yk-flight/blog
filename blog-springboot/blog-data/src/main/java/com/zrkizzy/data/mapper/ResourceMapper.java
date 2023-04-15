package com.zrkizzy.data.mapper;

import com.zrkizzy.data.dto.ResourceRoleDTO;

import java.util.List;

/**
 * 资源数据持久化接口
 *
 * @author zhangrongkang
 * @since 2023/3/16
 */
public interface ResourceMapper {

    /**
     * 加载资源角色数据
     *
     * @return 资源角色数据集合
     */
    List<ResourceRoleDTO> loadResourceRoleData();
}
