package com.zrkizzy.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrkizzy.data.domain.Module;
import com.zrkizzy.common.base.response.OptionsVO;

import java.util.List;

/**
 * 模块数据持久化接口
 *
 * @author zhangrongkang
 * @since 2023/3/16
 */
public interface ModuleMapper extends BaseMapper<Module> {

    /**
     * 获取模块选项集合
     *
     * @return 模块选项集合
     */
    List<OptionsVO> listModuleOptions();
}
