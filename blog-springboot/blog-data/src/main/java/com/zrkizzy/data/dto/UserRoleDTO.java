package com.zrkizzy.data.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户角色关联数据传输对象
 *
 * @author zhangrongkang
 * @since 2023/7/26
 */
@Data
public class UserRoleDTO implements Serializable {

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    /**
     * 角色ID
     */
    @NotNull(message = "角色ID不能为空")
    private Long roleId;

}
