package com.zrkizzy.data.dto;

import com.zrkizzy.common.annotation.IsEmail;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户登录数据传输对象
 *
 * @author zhangrongkang
 * @since 2023/4/12
 */
@Data
public class LoginDTO implements Serializable {

    /**
     * 用户名
     */
    @IsEmail
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String code;

    /**
     * 验证码追踪值
     */
    private String track;

}
