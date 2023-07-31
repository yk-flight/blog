package com.zrkizzy.server.service.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.data.domain.core.Resource;
import com.zrkizzy.data.dto.resource.ResourceDTO;
import com.zrkizzy.data.query.ResourceQuery;

/**
 * 资源业务逻辑接口
 *
 * @author zhangrongkang
 * @since 2023/7/27
 */
public interface IResourceService {

    /**
     * 获取所有资源
     *
     * @param resourceQuery 资源信息查询对象
     * @return 资源分页数据
     */
    Page<Resource> listResources(ResourceQuery resourceQuery);

    /**
     * 更新指定请求资源
     *
     * @param resourceDTO 资源数据接收对象
     * @return 是否更新成功
     */
    Boolean saveResource(ResourceDTO resourceDTO);

    /**
     * 获取指定资源信息
     *
     * @param resourceId 资源ID
     * @return 资源对象
     */
    Resource getResourceById(Long resourceId);

}