package com.zrkizzy.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮件发送数据传递对象
 *
 * @author zhangrongkang
 * @since 2023/5/5
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessageDTO {

    /**
     * 发件人
     */
    private String sender;

    /**
     * 收件人
     */
    private String senderTo;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件发送类型
     */
    private String type;

}
