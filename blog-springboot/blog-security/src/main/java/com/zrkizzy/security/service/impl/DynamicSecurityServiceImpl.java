package com.zrkizzy.security.service.impl;

import com.zrkizzy.common.utils.bean.BeanCopyUtil;
import com.zrkizzy.data.dto.resource.ResourceRoleDTO;
import com.zrkizzy.data.dto.resource.RoleSecurityDTO;
import com.zrkizzy.data.mapper.ResourceMapper;
import com.zrkizzy.security.service.DynamicSecurityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        List<ResourceRoleDTO> resourceRoleList = resourceMapper.loadResourceRoleData();
        Map<String, List<String>> map = new HashedMap<>();
        Map<String, ResourceRoleDTO> resourceRoleMap = new HashedMap<>();
        // 角色根据请求URL进行分组
        for (ResourceRoleDTO resourceRoleDTO : resourceRoleList) {
            String key = resourceRoleDTO.getUrl();
            // 如果当前key存在则获取已有的集合，如果不存在则创建一个新的集合
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(resourceRoleDTO.getRoleMark());
            map.put(key, list);

            resourceRoleMap.put(key, resourceRoleDTO);
        }
        List<RoleSecurityDTO> result = new ArrayList<>();
        // 此时已经根据请求路径将角色进行了区分
        for (String key : map.keySet()) {
            ResourceRoleDTO resourceRoleDTO = resourceRoleMap.get(key);
            RoleSecurityDTO roleSecurityDTO = BeanCopyUtil.copy(resourceRoleDTO, RoleSecurityDTO.class);
            // 赋值对象
            roleSecurityDTO.setRoles(map.get(key));
            result.add(roleSecurityDTO);
        }
        return result;
    }


}
