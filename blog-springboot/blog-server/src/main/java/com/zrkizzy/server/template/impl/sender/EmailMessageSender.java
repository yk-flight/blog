package com.zrkizzy.server.template.impl.sender;

import com.zrkizzy.data.dto.MessageSenderDTO;
import com.zrkizzy.server.template.AbstractMessageSender;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

import static com.zrkizzy.common.constant.MessageConst.*;

/**
 * 邮箱验证码发送具体类
 *
 * @author zhangrongkang
 * @since 2023/5/5
 */
@Service("emailMessageSender")
public class EmailMessageSender extends AbstractMessageSender {

    @Value("${blog.website}")
    private String website;
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送邮件信息到指定用户
     *
     * @param messageSenderDTO 消息发送数据传递对象
     * @throws Exception 邮件发送异常
     */
    @Override
    public void sendMessage(MessageSenderDTO messageSenderDTO) throws Exception {
        // 获取存储到Redis中的验证码
        String verifyCode = verifyCode(EMAIL, messageSenderDTO.getSenderTo());
        // 配置模板数据
        Map<String, Object> map = new HashMap<>();
        // 收件人昵称
        map.put(NICK_NAME, messageSenderDTO.getNickname());
        // 验证码
        map.put(VERIFY_CODE, verifyCode);
        // 网站域名
        map.put(WEBSITE, website);

        // 判断邮件发送的类型来定义邮件的模板
        String ftl = switch (messageSenderDTO.getType()) {
            // 修改密码验证码
            case CHANGE_PASSWORD -> "verifyEmail.ftl";
            // 管理员审核评论
            case REVIEW_COMMENT -> "";
            // 管理员审核留言
            case REVIEW_MESSAGE -> "";
            // 用户回复评论
            case REPLY_COMMENT -> "";
            default -> "";
        };

        // 定义复杂类型邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        // 邮件配置对象
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

        // 发件人
        messageHelper.setFrom(messageSenderDTO.getSender());
        // 收件人
        messageHelper.setTo(messageSenderDTO.getSenderTo());
        // 邮件主题
        messageHelper.setSubject(messageSenderDTO.getSubject());

        // 定义邮件的位置
        configuration.setClassForTemplateLoading(this.getClass(), "/templates/email/");
        // 解析Freemarker中的邮件内容
        String emailContent = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate(ftl), map);
        // 设置邮件内容并开启HTML
        messageHelper.setText(emailContent, true);

        // 发送邮件
        javaMailSender.send(mimeMessage);
    }
}
