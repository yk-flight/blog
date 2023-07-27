package com.zrkizzy.common.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.zrkizzy.common.constant.RabbitMqConst.*;

/**
 * RabbitMQ配置类
 *
 * @author zhangrongkang
 * @since 2023/5/4
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue emailQueue() {
        // 创建队列并持久化
        return new Queue(EMAIL_QUEUE, true);
    }

    @Bean
    public DirectExchange emailExchange() {
        // 创建交换机并配置持久化，同时不接收未能匹配到队列的消息进行转发
        return new DirectExchange(EMAIL_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindingEmailDirect() {
        // 绑定队列到交换机并配置对应路由
        return BindingBuilder.bind(emailQueue()).to(emailExchange()).with(EMAIL_ROUTING);
    }

    @Bean
    public Queue operateLogQueue() {
        // 创建操作日志队列并配置队列持久化，即使RabbitMQ重启，当前队列也不会丢失
        return new Queue(OPERATE_LOG_QUEUE, true);
    }

    @Bean
    public FanoutExchange operateLogExchange() {
        // 绑定扇形交换机，并配置交换机持久化，第三个参数表示即使没有与交换机绑定的队列交换机也不用删除
        return new FanoutExchange(OPERATE_LOG_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindingOperateLogFanout() {
        // 绑定队列到扇形交换机
        return BindingBuilder.bind(operateLogQueue()).to(operateLogExchange());
    }

}
