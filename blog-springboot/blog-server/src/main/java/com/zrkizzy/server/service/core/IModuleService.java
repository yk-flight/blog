package com.zrkizzy.server.service.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.base.response.OptionsVO;
import com.zrkizzy.data.domain.Module;
import com.zrkizzy.data.dto.resource.ModuleDTO;
import com.zrkizzy.data.query.ModuleQuery;
import com.zrkizzy.data.vo.resource.ModuleTreeVO;

import java.util.List;

/**
 * 请求模块业务逻辑接口
 *
 * @author zhangrongkang
 * @since 2023/7/3
 */
public interface IModuleService {

    /**
     * 获取模块选项集合
     *
     * @return 模块选项集合
     */
    List<OptionsVO> listModuleOptions();

    /**
     * 分页获取资源模块
     *
     * @param moduleQuery 资源模块查询对象
     * @return 资源模块分页数据
     */
    Page<Module> listModules(ModuleQuery moduleQuery);

    /**
     * 添加或更新资源模块
     *
     * @param moduleDTO 资源模块数据接收对象
     * @return 是否添加/更新成功
     */
    Boolean saveModule(ModuleDTO moduleDTO);

    /**
     * 获取指定资源模块信息
     *
     * @param moduleId 资源模块ID
     * @return 资源模块对象
     */
    Module getModuleById(Long moduleId);

    /**
     * 批量删除资源模块数据
     *
     * @param ids 资源模块ID
     * @return true：删除成功，false：删除失败
     */
    Boolean deleteBatch(List<Long> ids);

    /**
     * 获取所有模块ID
     *
     * @return 模块ID集合
     */
    List<Long> getAllModuleId();

    /**
     * 获取所有模块树形数据集合
     *
     * @return 所有模块树形数据
     */
    List<ModuleTreeVO> getAllModuleTree();
}
