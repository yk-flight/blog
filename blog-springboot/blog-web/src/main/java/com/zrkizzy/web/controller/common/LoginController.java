package com.zrkizzy.web.controller.common;

import com.zrkizzy.common.annotation.LoginAnnotation;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.data.dto.LoginDTO;
import com.zrkizzy.server.service.core.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录控制器
 *
 * @author zhangrongkang
 * @since 2023/4/12
 */
@Api(tags = "用户登录控制器")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private IUserService userService;

    /**
     * 用户登录返回Token
     *
     * @param loginDTO 用户登录数据传输对象
     * @return 公共返回对象（Token）
     */
    @LoginAnnotation
    @ApiOperation("后台用户登录")
    @PostMapping("/admin")
    public Result<String> login(@RequestBody @Validated LoginDTO loginDTO) {
        return Result.success(userService.login(loginDTO));
    }
}
