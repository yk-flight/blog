package com.zrkizzy.server.service.core.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.exception.BusinessException;
import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.data.domain.ModuleResource;
import com.zrkizzy.data.mapper.ModuleResourceMapper;
import com.zrkizzy.data.query.ModuleResourceQuery;
import com.zrkizzy.data.vo.ResourceVO;
import com.zrkizzy.data.vo.resource.ResourceLeafVO;
import com.zrkizzy.data.vo.resource.ResourceTreeVO;
import com.zrkizzy.server.service.core.IModuleResourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源模块关联业务逻辑接口实现类
 * </p>
 *
 * @author zhangrongkang
 * @since 2023/7/27
 */
@Service
@Slf4j
public class ModuleResourceServiceImpl implements IModuleResourceService {

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @Autowired
    private ModuleResourceMapper moduleResourceMapper;

    /**
     * 分页获取指定模块请求资源
     *
     * @param moduleResourceQuery 模块资源关联查询对象
     * @return 指定模块请求资源
     */
    @Override
    public Page<ResourceVO> listByModuleId(ModuleResourceQuery moduleResourceQuery) {
        // 开启分页
        Page<ModuleResource> page = new Page<>(moduleResourceQuery.getCurrentPage(), moduleResourceQuery.getPageSize());
        return moduleResourceMapper.pageByModuleId(page, moduleResourceQuery);
    }

    /**
     * 根据模块ID获取当前模块可以添加的资源
     *
     * @param id 模块ID
     * @return 请求资源树形数据返回对象
     */
    @Override
    public List<ResourceTreeVO> listResourceById(Long id) {
        // 获取当前模块中所有可以添加的资源集合
        List<ResourceLeafVO> leafList = moduleResourceMapper.findMissingResourceByModuleId(id);
        Map<String, List<ResourceLeafVO>> map = new HashedMap<>();
        // 按照请求路径进行分组
        for (ResourceLeafVO leaf : leafList) {
            // 获取请求链接
            String url = leaf.getUrl();
            // 截取出前面分组标签
            String label = subLabelFromUrl(url);
            if (map.containsKey(label)) {
                // 获取当前组的资源
                List<ResourceLeafVO> resourceList = map.get(label);
                resourceList.add(leaf);
            } else {
                // 创建新的集合并添加当前请求
                List<ResourceLeafVO> resourceList = new ArrayList<>();
                resourceList.add(leaf);
                map.put(label, resourceList);
            }
        }
        List<ResourceTreeVO> result = new ArrayList<>();
        // 将Map转为请求转为树形返回对象
        for (String label : map.keySet()) {
            ResourceTreeVO resourceTreeVO = new ResourceTreeVO();
            resourceTreeVO.setLabel(label);
            resourceTreeVO.setChildren(map.get(label));
            result.add(resourceTreeVO);
        }
        return result;
    }

    /**
     * 通过模块ID获取当前模块已有的请求资源
     *
     * @param moduleId 模块ID
     * @return 当前模块中已有的资源ID集合
     */
    @Override
    public List<Long> listCheckById(Long moduleId) {
        // 获取并返回当前已有的ID集合
        return moduleResourceMapper.listCheckById(moduleId);
    }

    /**
     * 批量删除指定模块中资源
     *
     * @param ids 模块资源关联ID集合
     * @return 是否删除成功
     */
    @Override
    public Boolean delete(List<Long> ids) {
        return moduleResourceMapper.deleteBatchIds(ids) == ids.size();
    }

    /**
     * 从请求URL中截取出分组标签
     *
     * @param url 请求URL
     * @return 分组标签名称
     */
    private String subLabelFromUrl(String url) {
        // 定义要找到的字符
        char target = '/';
        // 出现次数
        int count = 0;
        StringBuilder result = new StringBuilder();
        // 循环遍历当前请求路径
        for (int i = 0; i < url.length(); i++) {
            // 如果当前位置是'/'，计数器加1
            if (url.charAt(i) == target) {
                count++;
            }
            // 如果出现第三次，直接将拼接的内容返回
            if (count == 3) {
                return result.toString();
            } else {
                result.append(url.charAt(i));
            }
        }
        // 抛出异常
        throw new BusinessException();
    }

}
