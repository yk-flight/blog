package com.zrkizzy.server.produce;

import com.alibaba.fastjson.JSON;
import com.zrkizzy.data.dto.EmailMessageDTO;
import com.zrkizzy.server.template.impl.EmailMessageSender;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.zrkizzy.common.constant.RabbitMqConst.EMAIL_QUEUE;

/**
 * 消息发送生产者
 *
 * @author zhangrongkang
 * @since 2023/5/5
 */
@Component
@RabbitListener(queues = EMAIL_QUEUE)
public class MessageProduce {
    /**
     * 邮件发送人
     */
    @Value("${spring.mail.username}")
    private String sender;
    @Autowired
    private EmailMessageSender emailMessageSender;

    @RabbitHandler
    public void process(byte[] data) {
        // 获取邮件数据传输对象
        EmailMessageDTO emailMessageDTO = JSON.parseObject(new String(data), EmailMessageDTO.class);
        emailMessageSender.sendMessage(emailMessageDTO);
    }

}
