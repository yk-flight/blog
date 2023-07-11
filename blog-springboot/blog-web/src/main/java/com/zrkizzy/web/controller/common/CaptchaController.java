package com.zrkizzy.web.controller.common;

import com.wf.captcha.ArithmeticCaptcha;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.service.IRedisService;
import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.data.vo.common.CaptChaVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private SnowFlakeUtil snowFlakeUtil;
    @Autowired
    private IRedisService redisService;

    @ApiOperation(value = "生成验证码")
    @GetMapping("/getCaptcha")
    public Result<CaptChaVO> getCaptcha() {
        // 生成算数类型验证码
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(140, 28, 3);
        String track = String.valueOf(snowFlakeUtil.nextId());
        // Redis存储验证码，过期时间为5分钟
        redisService.set(CAPTCHA_PREFIX + track, captcha.text(), FIVE_MINUTE);
        return Result.success(CaptChaVO.builder()
                // 验证码图片Base64编码
                .codeImage(captcha.toBase64())
                // 验证码唯一值
                .track(track).build());
    }

}
