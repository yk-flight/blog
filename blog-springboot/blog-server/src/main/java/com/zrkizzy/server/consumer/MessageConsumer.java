package com.zrkizzy.server.consumer;

import com.alibaba.fastjson.JSON;
import com.zrkizzy.data.dto.MessageSenderDTO;
import com.zrkizzy.server.factory.MessageSenderFactory;
import com.zrkizzy.server.template.AbstractMessageSender;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.zrkizzy.common.constant.RabbitMqConst.EMAIL_QUEUE;

/**
 * 邮件短信消费者
 *
 * @author zhangrongkang
 * @since 2023/5/5
 */
@Component
@RabbitListener(queues = EMAIL_QUEUE)
public class MessageConsumer {
    @Autowired
    private MessageSenderFactory messageSenderFactory;

    @RabbitHandler
    public void process(byte[] data) throws Exception {
        // 获取邮件数据传输对象
        MessageSenderDTO messageSenderDTO = JSON.parseObject(new String(data), MessageSenderDTO.class);
        // 获取具体消息发送实例
        AbstractMessageSender messageSender = messageSenderFactory.getInstance(messageSenderDTO.getMethod());
        // 调用钩子方法发送邮件
        messageSender.sendMessage(messageSenderDTO);
    }

}
