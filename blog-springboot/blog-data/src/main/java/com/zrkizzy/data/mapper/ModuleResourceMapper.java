package com.zrkizzy.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.data.domain.ModuleResource;
import com.zrkizzy.data.query.ModuleResourceQuery;
import com.zrkizzy.data.vo.ResourceVO;
import org.apache.ibatis.annotations.Param;

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
    Page<ResourceVO> listByModuleId(@Param("page") Page<ModuleResource> page, @Param("moduleResourceQuery") ModuleResourceQuery moduleResourceQuery);

}
