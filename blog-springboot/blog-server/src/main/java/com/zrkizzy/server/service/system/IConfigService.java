package com.zrkizzy.server.service.system;

import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.data.dto.ConfigDTO;
import com.zrkizzy.data.vo.ConfigVO;

import java.util.List;

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
     * 添加或更新系统配置
     *
     * @param configDTO 系统配置数据接收对象
     * @return 前端响应对象
     */
    Result<?> saveConfig(ConfigDTO configDTO);

    /**
     * 批量删除系统配置数据
     *
     * @param ids 系统配置ID
     * @return true：删除成功，false：删除失败
     */
    Boolean deleteBatch(List<Long> ids);

}