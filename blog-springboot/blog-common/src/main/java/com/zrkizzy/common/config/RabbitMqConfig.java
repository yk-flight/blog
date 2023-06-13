package com.zrkizzy.common.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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

}
