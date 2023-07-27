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

    /**
     * 创建消息发送队列
     *
     * @return 持久化消息发送队列
     */
    @Bean
    public Queue emailQueue() {
        // 创建队列并持久化
        return new Queue(EMAIL_QUEUE, true);
    }

    /**
     * 创建消息发送直连交换机
     *
     * @return 直连交换机
     */
    @Bean
    public DirectExchange emailExchange() {
        // 创建交换机并配置持久化，同时不接收未能匹配到队列的消息进行转发
        return new DirectExchange(EMAIL_EXCHANGE, true, false);
    }

    /**
     * 绑定发送邮件队列
     *
     * @return 交换机队列绑定对象
     */
    @Bean
    public Binding bindingEmailDirect() {
        // 绑定队列到交换机并配置对应路由
        return BindingBuilder.bind(emailQueue()).to(emailExchange()).with(EMAIL_ROUTING);
    }

    /**
     * 创建持久化操作日志队列
     *
     * @return 操作日志队列
     */
    @Bean
    public Queue operateLogQueue() {
        // 创建操作日志队列并配置队列持久化，即使RabbitMQ重启，当前队列也不会丢失
        return new Queue(OPERATE_LOG_QUEUE, true);
    }

    /**
     * 创建操作日志直连交换机
     *
     * @return 直连交换机
     */
    @Bean
    public DirectExchange operateLogExchange() {
        // 创建直连交换机，并配置交换机持久化，第三个参数表示即使没有与交换机绑定的队列交换机也不用删除
        return new DirectExchange(OPERATE_LOG_EXCHANGE, true, false);
    }

    /**
     * 绑定操作日志队列到直连交换机
     *
     * @return 交换机队列绑定对象
     */
    @Bean
    public Binding bindingOperateLogFanout() {
        // 绑定操作日志队列到直连交换机
        return BindingBuilder.bind(operateLogQueue()).to(operateLogExchange()).with(OPERATE_LOG_ROUTING);
    }

    /**
     * 创建持久化登录日志队列
     *
     * @return 登录日志队列
     */
    @Bean
    public Queue loginLogQueue() {
        // 创建登录日志队列并配置持久化
        return new Queue(LOGIN_LOG_QUEUE, true);
    }

    /**
     * 创建直连交换机
     *
     * @return 直连交换机
     */
    @Bean
    public DirectExchange loginLogExchange() {
        // 创建直连交换机，并配置交换机持久化，第三个参数表示即使没有与交换机绑定的队列交换机也不用删除
        return new DirectExchange(LOGIN_LOG_EXCHANGE, true, false);
    }

    /**
     * 绑定登录日志队列到直连交换机
     *
     * @return 交换机队列绑定对象
     */
    @Bean
    public Binding bindingLoginLogFanout() {
        // 绑定登录日志队列到直连交换机
        return BindingBuilder.bind(loginLogQueue()).to(loginLogExchange()).with(LOGIN_LOG_ROUTING);
    }

}
