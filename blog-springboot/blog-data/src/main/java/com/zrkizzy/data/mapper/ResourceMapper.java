package com.zrkizzy.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrkizzy.data.domain.core.Resource;
import com.zrkizzy.data.dto.resource.RoleSecurityDTO;

import java.util.List;

/**
 * 资源数据持久化接口
 *
 * @author zhangrongkang
 * @since 2023/3/16
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 加载资源角色数据
     *
     * @return 资源角色数据集合
     */
    List<RoleSecurityDTO> loadResourceRoleData();
}
