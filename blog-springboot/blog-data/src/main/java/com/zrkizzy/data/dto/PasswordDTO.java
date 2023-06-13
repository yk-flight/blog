package com.zrkizzy.data.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户更新密码数据传输对象
 *
 * @author zhangrongkang
 * @since 2023/5/4
 */
@Data
public class PasswordDTO implements Serializable {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String code;

    /**
     * 用户新密码
     */
    @NotBlank(message = "用户密码不能为空")
    @Length(min = 6, max = 20)
    private String password;

}
