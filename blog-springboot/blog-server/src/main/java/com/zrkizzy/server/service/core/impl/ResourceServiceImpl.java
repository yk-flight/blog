package com.zrkizzy.server.service.core.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.common.utils.bean.BeanCopyUtil;
import com.zrkizzy.data.domain.Resource;
import com.zrkizzy.data.dto.ResourceDTO;
import com.zrkizzy.data.mapper.ResourceMapper;
import com.zrkizzy.data.query.ResourceQuery;
import com.zrkizzy.server.service.core.IResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 资源业务逻辑接口实现类
 * </p>
 *
 * @author zhangrongkang
 * @since 2023/7/27
 */
@Service
@Slf4j
public class ResourceServiceImpl implements IResourceService {

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 获取所有资源
     *
     * @param resourceQuery 资源查询对象
     * @return 资源分页数据
     */
    @Override
    public Page<Resource> listResources(ResourceQuery resourceQuery) {
        // 开启分页
        Page<Resource> page = new Page<>(resourceQuery.getCurrentPage(), resourceQuery.getPageSize());
        // 定义查询条件
        QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
        // 资源名称
        if (StringUtils.hasLength(resourceQuery.getName())) {
            queryWrapper.eq("name", resourceQuery.getName());
        }
        // 资源请求方式
        if (StringUtils.hasLength(resourceQuery.getMethod())) {
            queryWrapper.eq("method", resourceQuery.getMethod());
        }
        // 资源请求路径
        if (StringUtils.hasLength(resourceQuery.getUrl())) {
            queryWrapper.eq("url", resourceQuery.getUrl());
        }
        // 获取时间范围
        List<String> dataRange = resourceQuery.getDataRange();
        // 如果时间范围不为空
        if (!CollectionUtils.isEmpty(dataRange)) {
            // 拼接时间范围查询条件
            queryWrapper.between("create_time", dataRange.get(0), dataRange.get(1));
        }
        // 查询分页
        return resourceMapper.selectPage(page, queryWrapper);
    }

    /**
     * 添加或更新资源
     *
     * @param resourceDTO 资源数据接收对象
     * @return 是否添加/更新成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveResource(ResourceDTO resourceDTO) {
        log.info("--------------------- 开始进行新增-更新操作 ---------------------");
        log.info("resourceDTO: {}", resourceDTO);
        // 根据是否包含ID来判断添加-更新操作
        if (null != resourceDTO.getId()) {
            // 更新资源
            return updateResource(resourceDTO);
        }
        // 添加资源
        return insertResource(resourceDTO);
    }

    /**
     * 获取指定资源信息
     *
     * @param resourceId 资源ID
     * @return 资源数据返回对象
     */
    @Override
    public Resource getResourceById(Long resourceId) {
        return resourceMapper.selectById(resourceId);
    }
    
    /**
     * 批量删除资源数据
     *
     * @param ids 资源ID
     * @return true：删除成功，false：删除失败
     */
    @Override
    public Boolean deleteBatch(List<Long> ids) {
        return resourceMapper.deleteBatchIds(ids) == ids.size();
    }

    /**
     * 更新当前资源
     *
     * @param resourceDTO 资源数据接收对象
     * @return 是否更新成功
     */
    private Boolean updateResource(ResourceDTO resourceDTO) {
        log.info("--------------------- 执行更新操作 ---------------------");
        // 对资源进行更新操作并返回响应结果
        return resourceMapper.updateById(BeanCopyUtil.copy(resourceDTO, Resource.class)) == 1;
    }
    
    /**
     * 添加新的资源
     *
     * @param resourceDTO 资源数据接收对象
     * @return 是否添加成功
     */
    private Boolean insertResource(ResourceDTO resourceDTO) {
        log.info("--------------------- 开始进行新增操作 ---------------------");
        // 生成资源ID
        Long id = snowFlakeUtil.nextId();
        // 设置ID
        resourceDTO.setId(id);
        // 添加资源数据并返回添加结果
        return resourceMapper.insert(BeanCopyUtil.copy(resourceDTO, Resource.class)) == 1;
    }

}
