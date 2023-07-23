package com.zrkizzy.server.service.system;

import com.zrkizzy.data.dto.ConfigDTO;
import com.zrkizzy.data.vo.ConfigVO;

/**
 * 系统配置业务逻辑接口
 *
 * @author zhangrongkang
 * @since 2023/7/21
 */
public interface IConfigService {

    /**
     * 获取所有系统配置
     *
     * @return 系统配置数据返回对象
     */
    ConfigVO getConfig();

    /**
     * 更新系统基本配置
     *
     * @param configDTO 系统配置数据接收对象
     * @return 受影响行数
     */
    Integer saveConfig(ConfigDTO configDTO);

}