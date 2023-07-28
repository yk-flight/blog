package com.zrkizzy.server.service.core.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.data.domain.ModuleResource;
import com.zrkizzy.data.mapper.ModuleResourceMapper;
import com.zrkizzy.data.query.ModuleResourceQuery;
import com.zrkizzy.data.vo.ResourceVO;
import com.zrkizzy.server.service.core.IModuleResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return moduleResourceMapper.listByModuleId(page, moduleResourceQuery);
    }

}
