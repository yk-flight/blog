package com.zrkizzy.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrkizzy.data.domain.ModuleRole;
import com.zrkizzy.data.vo.resource.ModuleTreeVO;

import java.util.List;

/**
 * 模块角色数据持久化接口
 *
 * @author zhangrongkang
 * @since 2023/3/16
 */
public interface ModuleRoleMapper extends BaseMapper<ModuleRole> {

    /**
     * 删除指定角色具有的所有请求权限
     *
     * @param roleId 角色ID
     * @return 受影响行数
     */
    Integer deleteByRoleId(Long roleId);

    /**
     * 根据角色ID获取角色模块权限
     *
     * @param roleId 角色ID
     * @return 模块树形数据返回对象
     */
    List<ModuleTreeVO> listModuleByRoleId(Long roleId);
}
