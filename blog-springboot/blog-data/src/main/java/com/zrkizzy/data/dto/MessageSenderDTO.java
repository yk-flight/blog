package com.zrkizzy.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 消息发送数据传输对象
 *
 * @author zhangrongkang
 * @since 2023/5/5
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageSenderDTO implements Serializable {

    /**
     * 发送人
     */
    private String sender;

    /**
     * 接收人
     */
    private String senderTo;

    /**
     * 接收人昵称
     */
    private String nickname;

    /**
     * 消息主题
     */
    private String subject;

    /**
     * 消息发送类型
     */
    private String type;

    /**
     * 消息发送方式：email: 邮件 sms：短信
     */
    private String method;

}
