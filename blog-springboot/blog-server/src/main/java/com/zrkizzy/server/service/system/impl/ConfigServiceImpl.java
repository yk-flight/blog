package com.zrkizzy.server.service.system.impl;

import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.common.utils.bean.BeanCopyUtil;
import com.zrkizzy.data.domain.Config;
import com.zrkizzy.data.dto.ConfigDTO;
import com.zrkizzy.data.mapper.ConfigMapper;
import com.zrkizzy.data.vo.ConfigVO;
import com.zrkizzy.server.service.system.IConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 系统配置业务逻辑接口实现类
 * </p>
 *
 * @author zhangrongkang
 * @since 2023/7/21
 */
@Service
@Slf4j
public class ConfigServiceImpl implements IConfigService {

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @Autowired
    private ConfigMapper configMapper;


    /**
     * 获取所有系统配置
     *
     * @return 系统配置数据返回对象
     */
    @Override
    public ConfigVO getConfig() {
        // 定义返回结果
        // 如果Redis中的配置信息不为空

        return BeanCopyUtil.copy(configMapper.selectList(null).get(0), ConfigVO.class);
    }

    /**
     * 添加或更新系统配置
     *
     * @param configDTO 系统配置数据接收对象
     * @return 前端响应对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> saveConfig(ConfigDTO configDTO) {
        log.info("--------------------- 开始进行新增-更新操作 ---------------------");
        log.info("configDTO: {}", configDTO);
        // 根据是否包含ID来判断添加-更新操作
        if (null != configDTO.getId()) {
            // 更新系统配置
            return updateConfig(configDTO);
        }
        // 添加系统配置
        return insertConfig(configDTO);
    }
    
    /**
     * 批量删除系统配置数据
     *
     * @param ids 系统配置ID
     * @return true：删除成功，false：删除失败
     */
    @Override
    public Boolean deleteBatch(List<Long> ids) {
        return configMapper.deleteBatchIds(ids) == ids.size();
    }

    /**
     * 更新当前系统配置
     *
     * @param configDTO 系统配置数据接收对象
     * @return 前端响应对象
     */
    private Result<?> updateConfig(ConfigDTO configDTO) {
        log.info("--------------------- 执行更新操作 ---------------------");
        // 对系统配置进行更新操作并返回响应结果
        return configMapper.updateById(BeanCopyUtil.copy(configDTO, Config.class)) == 1 ?
        Result.success() : Result.failure();
    }
    
    /**
     * 添加新的系统配置
     *
     * @param configDTO 系统配置数据接收对象
     * @return 前端响应对象
     */
    private Result<ConfigVO> insertConfig(ConfigDTO configDTO) {
        log.info("--------------------- 开始进行新增操作 ---------------------");
        // 生成系统配置ID
        Long id = snowFlakeUtil.nextId();
        // 设置ID
        configDTO.setId(id);
        // 添加系统配置数据并返回添加结果
        return configMapper.insert(BeanCopyUtil.copy(configDTO, Config.class)) == 1 ?
            Result.success() : Result.failure();
    }

}
