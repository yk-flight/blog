package com.zrkizzy.security.service.impl;

import com.zrkizzy.data.dto.resource.RoleSecurityDTO;
import com.zrkizzy.data.mapper.ResourceMapper;
import com.zrkizzy.security.service.DynamicSecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 动态权限业务接口实现类
 *
 * @author zhangrongkang
 * @since 2023/3/13
 */
@Slf4j
@Service
public class DynamicSecurityServiceImpl implements DynamicSecurityService {

    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 加载资源角色数据
     *
     * @return 资源角色数据集合
     */
    @Override
    public List<RoleSecurityDTO> loadResourceRoleData() {
        // 获取到角色与资源对应关系
        return resourceMapper.loadResourceRoleData();
    }

}
