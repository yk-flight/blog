package com.zrkizzy.data.dto.core;

import com.zrkizzy.common.annotation.IsEmail;
import com.zrkizzy.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * 用户数据传输对象
 *
 * @author zhangrongkang
 * @since 2023/7/15
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class UserDTO extends BaseDTO {


    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @IsEmail
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 状态，0：禁用，1：启用
     */
    @NotNull(message = "用户状态不能为空")
    private Boolean status;

    /**
     * 备注
     */
    private String remark;

}
