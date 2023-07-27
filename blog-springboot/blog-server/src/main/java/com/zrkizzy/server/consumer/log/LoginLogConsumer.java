package com.zrkizzy.server.consumer.log;

import com.alibaba.fastjson.JSON;
import com.zrkizzy.common.constant.RabbitMqConst;
import com.zrkizzy.data.domain.LoginInfo;
import com.zrkizzy.data.mapper.LoginInfoMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 登录日志消费者
 *
 * @author zhangrongkang
 * @since 2023/7/27
 */
@Component
@RabbitListener(queues = RabbitMqConst.LOGIN_LOG_QUEUE, ackMode = "AUTO")
public class LoginLogConsumer {
    @Autowired
    private LoginInfoMapper loginInfoMapper;

    @RabbitHandler
    public void process(byte[] data) {
        // 从RabbitMQ中拿到操作日志数据，并采取自动确认策略
        LoginInfo loginInfo = JSON.parseObject(new String(data), LoginInfo.class);
        // 发送操作日志到数据库中
        if (null != loginInfo) {
            loginInfoMapper.insert(loginInfo);
        }
    }

}
