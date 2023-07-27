package com.zrkizzy.server.service.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.data.domain.Resource;
import com.zrkizzy.data.dto.ResourceDTO;
import com.zrkizzy.data.query.ResourceQuery;

import java.util.List;

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
     * 添加或更新资源
     *
     * @param resourceDTO 资源数据接收对象
     * @return 是否添加/更新成功
     */
    Boolean saveResource(ResourceDTO resourceDTO);

    /**
     * 获取指定资源信息
     *
     * @param resourceId 资源ID
     * @return 资源对象
     */
    Resource getResourceById(Long resourceId);

    /**
     * 批量删除资源数据
     *
     * @param ids 资源ID
     * @return true：删除成功，false：删除失败
     */
    Boolean deleteBatch(List<Long> ids);

}