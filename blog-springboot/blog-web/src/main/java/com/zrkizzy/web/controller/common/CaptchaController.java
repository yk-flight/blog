package com.zrkizzy.web.controller.common;

import com.wf.captcha.ArithmeticCaptcha;
import com.zrkizzy.common.service.IRedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.zrkizzy.common.constant.CommonConst.*;
import static com.zrkizzy.common.constant.RedisConst.CAPTCHA_PREFIX;
import static com.zrkizzy.common.constant.TimeConst.FIVE_MINUTE;

/**
 * 验证码控制器
 *
 * @author zhangrongkang
 * @since 2023/4/13
 */
@Api(tags = "验证码控制器")
@RestController
@RequestMapping("/captcha")
public class CaptchaController {
    @Autowired
    private IRedisService redisService;

    @ApiOperation(value = "生成验证码", produces = "image/jpeg")
    @GetMapping("/getCaptcha")
    public void getCaptcha(HttpServletResponse response) {
        // 设置请求头输出为图片类型
        response.setContentType(IMAGE_JPEG);
        // 设置请求头
        response.setHeader(PRAGMA, NO_CACHE);
        response.setHeader(CACHE_CONTROL, NO_CACHE);
        // 永不失效
        response.setDateHeader(EXPIRES, 0);
        // 生成算数类型验证码
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(140, 28, 3);
        // Redis存储验证码，过期时间为5分钟
        redisService.set(CAPTCHA_PREFIX, captcha.text(), FIVE_MINUTE);
        try {
            // 输出验证码
            captcha.out(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
