package com.zrkizzy.server.service.system.impl;

import com.zrkizzy.common.constant.RedisConst;
import com.zrkizzy.common.exception.BusinessException;
import com.zrkizzy.common.service.IRedisService;
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

import javax.annotation.PostConstruct;

import static com.zrkizzy.common.enums.HttpStatusEnum.UPDATE_ERROR;

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
    private IRedisService redisService;

    @Autowired
    private ConfigMapper configMapper;



    /**
     * 获取所有系统配置
     *
     * @return 系统配置数据返回对象
     */
    @Override
    public ConfigVO getConfig() {
        // 从Redis中获取基本配置信息
        ConfigVO configVO = redisService.get(RedisConst.CONFIG_BASIC_KEY, ConfigVO.class);
        if (null == configVO) {
            // 从数据库中获取基本配置信息
            configVO = loadBasicConfig();
        }
        return configVO;
    }

    /**
     * 更新系统基本配置
     *
     * @param configDTO 系统配置数据接收对象
     * @return 受影响行数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer saveConfig(ConfigDTO configDTO) {
        // 转换数据接收对象
        Config config = BeanCopyUtil.copy(configDTO, Config.class);
        int row = configMapper.updateById(config);
        if (row != 1) {
            // 抛出更新失败异常
            throw new BusinessException(UPDATE_ERROR);
        }
        // 采取先更新数据库再更新Redis策略，清除掉Redis中存储的配置信息
        redisService.del(RedisConst.CONFIG_BASIC_KEY);
        // 存储新的键值到Redis中，仍然为永不过期
        redisService.set(RedisConst.CONFIG_BASIC_KEY, BeanCopyUtil.copy(config, ConfigVO.class));
        // 返回受影响行数
        return row;
    }

    /**
     * 获取系统基本配置信息
     *
     * @return 基本信息返回对象
     */
    @PostConstruct
    private ConfigVO loadBasicConfig() {
        // 获取到配置信息
        ConfigVO configVO = BeanCopyUtil.copy(configMapper.selectList(null).get(0), ConfigVO.class);
        // 存储到Redis中，并设置永不过期
        redisService.set(RedisConst.CONFIG_BASIC_KEY, configVO);
        return configVO;
    }

}
