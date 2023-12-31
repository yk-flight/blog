package com.zrkizzy.server.consumer.log;

import com.alibaba.fastjson.JSON;
import com.zrkizzy.common.constant.RabbitMqConst;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.exception.BusinessException;
import com.zrkizzy.data.domain.OperateLog;
import com.zrkizzy.data.mapper.OperateLogMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 操作日志消费者
 *
 * @author zhangrongkang
 * @since 2023/7/27
 */
@Component
@RabbitListener(queues = RabbitMqConst.OPERATE_LOG_QUEUE)
public class OperateLogConsumer {

    @Autowired
    private OperateLogMapper operateLogLogMapper;

    @RabbitHandler
    public void process(byte[] data) {
        // 从RabbitMQ中拿到操作日志数据，并采取自动确认策略
        OperateLog operateLog = JSON.parseObject(new String(data), OperateLog.class);
        // 发送操作日志到数据库中
        if (null == operateLog) {
            throw new BusinessException(HttpStatusEnum.OPERATE_SAVE_ERROR);
        }
        operateLogLogMapper.insert(operateLog);
    }

}
