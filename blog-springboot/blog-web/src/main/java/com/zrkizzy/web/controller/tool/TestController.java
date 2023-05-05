package com.zrkizzy.web.controller.tool;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.Date;

/**
 * 系统创建测试类
 *
 * @author zhangrongkang
 * @since 2023/3/6
 */
@Api(tags = "系统创建测试类")
@RestController
@RequestMapping("/test")
public class TestController {
    /**
     * 邮件发送人
     */
    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @ApiOperation("获取邮件发送人")
    @GetMapping("/getEmailFrom")
    public String getEmailFrom() {
        return sender;
    }

    @ApiOperation("测试发送邮件")
    @GetMapping("/email")
    public void sendEmail() {
        try {
            // true 代表支持复杂的类型
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(),true);
            // 邮件发信人
            mimeMessageHelper.setFrom("1072876976@qq.com");
            // 邮件收信人
            mimeMessageHelper.setTo("1072876976@qq.com");
            // 邮件主题
            mimeMessageHelper.setSubject("测试邮件发送功能");
            // 邮件内容
            mimeMessageHelper.setText("当你看到我的时候就已经成功了");
            // 邮件发送时间
            mimeMessageHelper.setSentDate(new Date());
            // 发送邮件
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            System.out.println("发送邮件成功");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("发送邮件失败："+e.getMessage());
        }
    }
}
