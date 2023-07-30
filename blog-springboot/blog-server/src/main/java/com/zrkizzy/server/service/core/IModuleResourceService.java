package com.zrkizzy.server.service.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.data.query.ModuleResourceQuery;
import com.zrkizzy.data.vo.ResourceTreeVO;
import com.zrkizzy.data.vo.ResourceVO;

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
}