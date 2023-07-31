package com.zrkizzy.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.data.domain.ModuleResource;
import com.zrkizzy.data.query.ModuleResourceQuery;
import com.zrkizzy.data.vo.resource.ResourceVO;
import com.zrkizzy.data.vo.resource.ResourceLeafVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源模块关联数据持久化接口
 * </p>
 *
 * @author zhangrongkang
 * @since 2023/7/27
 */
public interface ModuleResourceMapper extends BaseMapper<ModuleResource> {

    /**
     * 分页查询指定模块对应请求资源
     *
     * @param page 分页对象
     * @param moduleResourceQuery 模块资源查询对象
     * @return 请求资源返回对象
     */
    Page<ResourceVO> pageByModuleId(@Param("page") Page<ModuleResource> page, @Param("moduleResourceQuery") ModuleResourceQuery moduleResourceQuery);

    /**
     * 获取当前模块中所有可以添加的资源集合
     *
     * @param moduleId 模块ID
     * @return 指定模块可以添加请求资源叶子集合
     */
    List<ResourceLeafVO> findMissingResourceByModuleId(Long moduleId);

    /**
     * 通过模块ID获取当前模块已有的请求资源
     *
     * @param moduleId 模块ID
     * @return 当前模块中已有的资源ID集合
     */
    List<Long> listCheckById(Long moduleId);

    /**
     * 删除指定模块对应数据
     *
     * @param moduleId 模块ID
     * @return 受影响行数
     */
    Integer deleteByModuleId(Long moduleId);

    /**
     * 批量插入模块资源数据
     *
     * @param moduleResourceList 模块资源集合
     * @return 受影响行数
     */
    Integer insertBatch(@Param("moduleResourceList") List<ModuleResource> moduleResourceList);

}
