package com.zrkizzy.common.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.stereotype.Component;

/**
 * <p>Lettuce连接工厂校验配置类</p>
 *
 * <p>确保在获取连接之前，Lettuce会验证连接的可用性，如果连接不可用，Lettuce将尝试重新建立连接或抛出异常，提高应用程序对Redis的稳定性和可靠性</p>
 *
 * @author zhangrongkang
 * @since 2023/7/5
 */
@Component
public class LettuceConnectionValidConfig implements InitializingBean {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 确保当前使用的Redis连接工厂基于Lettuce
        if (redisConnectionFactory instanceof LettuceConnectionFactory lettuceConnectionFactory) {
            // 获取连接时进行连接验证，确保连接可用
            lettuceConnectionFactory.setValidateConnection(true);
        }
    }
}