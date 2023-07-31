package com.zrkizzy.server.service.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.data.dto.resource.ModuleResourceDTO;
import com.zrkizzy.data.query.ModuleResourceQuery;
import com.zrkizzy.data.vo.resource.ResourceVO;
import com.zrkizzy.data.vo.resource.ResourceTreeVO;

import java.util.List;

/**
 * 资源模块关联业务逻辑接口
 *
 * @author zhangrongkang
 * @since 2023/7/27
 */
public interface IModuleResourceService {

    /**
     * 分页获取指定模块请求资源
     *
     * @param moduleResourceQuery 模块资源关联查询对象
     * @return 指定模块请求资源
     */
    Page<ResourceVO> listByModuleId(ModuleResourceQuery moduleResourceQuery);

    /**
     * 根据模块ID获取当前模块可以添加的资源
     *
     * @param id 模块ID
     * @return 请求资源树形数据集合
     */
    List<ResourceTreeVO> listResourceById(Long id);

    /**
     * 通过模块ID获取当前模块已有的请求资源
     *
     * @param moduleId 模块ID
     * @return 当前模块中已有的资源ID集合
     */
    List<Long> listCheckById(Long moduleId);

    /**
     * 批量删除指定模块中资源
     *
     * @param ids 模块资源关联ID集合
     * @return 是否删除成功
     */
    Boolean delete(List<Long> ids);

    /**
     * 为指定模块分配资源请求
     *
     * @param moduleResourceDTO 模块资源关联对象
     * @return 是否保存成功
     */
    Boolean save(ModuleResourceDTO moduleResourceDTO);

}