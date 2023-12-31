package com.zrkizzy.web.controller.system;

import com.alibaba.fastjson.JSON;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.data.dto.MessageSenderDTO;
import com.zrkizzy.security.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zrkizzy.common.constant.MessageConst.CHANGE_PASSWORD;
import static com.zrkizzy.common.constant.MessageConst.EMAIL;
import static com.zrkizzy.common.constant.RabbitMqConst.EMAIL_EXCHANGE;
import static com.zrkizzy.common.constant.RabbitMqConst.EMAIL_ROUTING;

/**
 * 邮件控制器
 *
 * @author zhangrongkang
 * @since 2023/5/4
 */
@Api(tags = "邮件发送控制器")
@RestController
@RequestMapping("/admin/email")
public class EmailController {
    /**
     * 邮件发送人
     */
    @Value("${spring.mail.username}")
    private String sender;
    @Autowired
    private SecurityUtil securityUtil;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ApiOperation("修改密码验证码")
    @GetMapping("/password")
    public Result<?> password() {
        // 定义邮件发送对象
        MessageSenderDTO messageSenderDTO = MessageSenderDTO.builder()
                // 发送方式
                .method(EMAIL)
                // 发件人
                .sender(sender)
                // 收件人
                .senderTo(securityUtil.getLoginUsername())
                // 收件人昵称
                .nickname(securityUtil.getLoginUserNickname())
                // 邮件类型标识
                .type(CHANGE_PASSWORD)
                // 邮件主题
                .subject("验证身份").build();
        // 将邮件数据传输对象发送到RabbitMQ中
        rabbitTemplate.convertAndSend(EMAIL_EXCHANGE, EMAIL_ROUTING, new Message(JSON.toJSONBytes(messageSenderDTO), new MessageProperties()));
        // 返回成功结果，如果失败则会被全局异常处理捕获
        return Result.success();
    }
}
