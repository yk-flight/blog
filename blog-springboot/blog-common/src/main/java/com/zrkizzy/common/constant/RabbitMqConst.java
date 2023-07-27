package com.zrkizzy.common.constant;

/**
 * Rabbit全局静态常量
 *
 * @author zhangrongkang
 * @since 2023/5/4
 */
public class RabbitMqConst {

    /**
     * 邮件信息发送队列
     */
    public static final String EMAIL_QUEUE = "email_queue";

    /**
     * 邮件发送交换机
     */
    public static final String EMAIL_EXCHANGE = "email_exchange";

    /**
     * 邮件发送路由
     */
    public static final String EMAIL_ROUTING = "email_routing";

    /**
     * 操作日志队列
     */
    public static final String OPERATE_LOG_QUEUE = "operate_log_queue";

    /**
     * 登录日志队列
     */
    public static final String LOGIN_LOG_QUEUE = "login_log_queue";

    /**
     * 日志交换机
     */
    public static final String LOG_EXCHANGE = "log_exchange";

}
