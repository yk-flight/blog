package com.zrkizzy.common.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.zrkizzy.common.constant.RabbitMqConst.EMAIL_EXCHANGE;
import static com.zrkizzy.common.constant.RabbitMqConst.EMAIL_QUEUE;

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
        return new Queue(EMAIL_QUEUE, true);
    }

    @Bean
    public FanoutExchange emailExchange() {
        return new FanoutExchange(EMAIL_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindingEmailDirect() {
        return BindingBuilder.bind(emailQueue()).to(emailExchange());
    }

}
