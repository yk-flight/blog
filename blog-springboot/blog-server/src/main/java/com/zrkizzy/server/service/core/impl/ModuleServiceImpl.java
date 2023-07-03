package com.zrkizzy.server.service.core.impl;

import com.zrkizzy.data.mapper.ModuleMapper;
import com.zrkizzy.data.vo.monitor.OptionsVO;
import com.zrkizzy.server.service.core.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 请求模块业务逻辑接口实现类
 *
 * @author zhangrongkang
 * @since 2023/7/3
 */
@Service
public class ModuleServiceImpl implements IModuleService {

    @Autowired
    private ModuleMapper moduleMapper;

    /**
     * 获取模块选项集合
     *
     * @return 模块选项集合
     */
    @Override
    public List<OptionsVO> listModuleOptions() {
        return moduleMapper.listModuleOptions();
    }
}
