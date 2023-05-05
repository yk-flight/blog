package com.zrkizzy.server.template;

import com.zrkizzy.common.service.IRedisService;
import com.zrkizzy.data.dto.EmailMessageDTO;
import com.zrkizzy.security.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

import static com.zrkizzy.common.constant.MessageConst.EMAIL;
import static com.zrkizzy.common.constant.MessageConst.SMS;
import static com.zrkizzy.common.constant.RedisConst.*;
import static com.zrkizzy.common.constant.TimeConst.FIVE_MINUTE;

/**
 * 消息发送模板类
 *
 * @author zhangrongkang
 * @since 2023/5/5
 */
@Component
public abstract class MessageSenderTemplate {
    @Autowired
    private SecurityUtil securityUtil;
    @Autowired
    private IRedisService redisService;

    /**
     * 发送邮件信息到指定用户
     *
     * @param emailMessageDTO 邮件发送数据传递对象
     */
    public abstract void sendMessage(EmailMessageDTO emailMessageDTO);

    /**
     * 生成当前登录用户验证码并存储到Redis中
     *
     * @param senderTo 邮件接收人
     * @param method 邮件发送方式
     * @return 验证码
     */
    public String verifyCode(String method, String senderTo) {
        // 定义Redis中的存储前缀
        String prefix = null;
        // 随机生成六位验证码
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
        prefix = switch (method) {
            // 电子邮箱验证码
            case EMAIL -> CAPTCHA_EMAIL_PREFIX;
            // 手机短信验证码
            case SMS -> CAPTCHA_SMS_PREFIX;
            // 默认前缀为空
            default -> "";
        };
        // 存储验证码到Redis，过期时间五分钟
        redisService.set(prefix + senderTo, verifyCode, FIVE_MINUTE);
        // 将验证码返回
        return verifyCode;
    }

}
