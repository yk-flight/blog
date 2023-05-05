package com.zrkizzy.server.factory;

import com.zrkizzy.server.template.AbstractMessageSender;
import com.zrkizzy.server.template.impl.EmailMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.zrkizzy.common.constant.MessageConst.EMAIL;
import static com.zrkizzy.common.constant.MessageConst.SMS;

/**
 * 消息发送工厂
 *
 * @author zhangrongkang
 * @since 2023/5/5
 */
@Component
public class MessageSenderFactory {
    @Autowired
    private EmailMessageSender emailMessageSender;

    /**
     * 根据发送消息类型来注入不同的实例
     *
     * @param method 消息类型 email：邮件 sms：短信
     * @return 具体消息发送类
     */
    public AbstractMessageSender getInstance(String method) {
        AbstractMessageSender messageSender = null;
        // 根据类型判断注入哪种实例
        messageSender = switch (method) {
            // 发送邮件
            case EMAIL -> emailMessageSender;
            // 发送短信
            case SMS -> null;
            default -> null;
        };
        // 返回具体实例
        return messageSender;
    }
}
