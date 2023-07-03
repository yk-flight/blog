package com.zrkizzy.server.service.core;

import com.zrkizzy.data.vo.monitor.OptionsVO;

import java.util.List;

/**
 * 请求模块业务逻辑接口
 *
 * @author zhangrongkang
 * @since 2023/7/3
 */
public interface IModuleService {

    /**
     * 获取模块选项集合
     *
     * @return 模块选项集合
     */
    List<OptionsVO> listModuleOptions();
}
