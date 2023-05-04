package com.zrkizzy.data.dto;

import com.zrkizzy.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * 用户个人信息数据传输对象
 *
 * @author zhangrongkang
 * @since 2023/5/3
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class UserInfoDTO extends BaseDTO {

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 备注
     */
    @NotBlank(message = "手机号不能为空")
    private String phone;

    /**
     * 备注
     */
    private String remark;

}
