package com.zrkizzy.server.template.impl.sender;

import com.zrkizzy.data.dto.MessageSenderDTO;
import com.zrkizzy.server.template.AbstractMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static com.zrkizzy.common.constant.MessageConst.*;

/**
 * 邮箱验证码发送具体类
 *
 * @author zhangrongkang
 * @since 2023/5/5
 */
@Service("emailMessageSender")
public class EmailMessageSender extends AbstractMessageSender {
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送邮件信息到指定用户
     *
     * @param messageSenderDTO 消息发送数据传递对象
     */
    @Override
    public void sendMessage(MessageSenderDTO messageSenderDTO) {
        // 获取存储到Redis中的验证码
        String verifyCode = verifyCode(EMAIL, messageSenderDTO.getSenderTo());
        // 判断邮件发送的类型来定义邮件内容
        String content = switch (messageSenderDTO.getType()) {
            // 修改密码验证码
            case CHANGE_PASSWORD ->
                    "【雅康博客】您正在修改您的登录密码，您的验证码是 " + verifyCode + " ，有效时间为5分钟，请及时更改。转发给他人可能导致账号被盗，请勿泄漏，谨防被骗。";
            // 管理员审核评论
            case REVIEW_COMMENT -> "";
            // 管理员审核留言
            case REVIEW_MESSAGE -> "";
            // 用户回复评论
            case REPLY_COMMENT -> "";
            default -> "";
        };
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 发件人
        simpleMailMessage.setFrom(messageSenderDTO.getSender());
        // 收件人
        simpleMailMessage.setTo(messageSenderDTO.getSenderTo());
        // 邮件主题
        simpleMailMessage.setSubject(messageSenderDTO.getSubject());
        // 邮件内容
        simpleMailMessage.setText(content);

        // 发送邮件
        javaMailSender.send(simpleMailMessage);
    }
}
