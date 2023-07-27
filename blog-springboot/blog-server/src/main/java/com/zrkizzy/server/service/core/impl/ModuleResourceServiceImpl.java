package com.zrkizzy.server.service.core.impl;

import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.data.mapper.ModuleResourceMapper;
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

}
