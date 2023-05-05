package com.zrkizzy.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息发送数据传递对象
 *
 * @author zhangrongkang
 * @since 2023/5/5
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    /**
     * 发送人
     */
    private String sender;

    /**
     * 接收人
     */
    private String senderTo;

    /**
     * 消息主题
     */
    private String subject;

    /**
     * 消息发送类型
     */
    private String type;

}
