package com.zrkizzy.data.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户头像数据传输对象
 *
 * @author zhangrongkang
 * @since 2023/5/30
 */
@Data
public class AvatarDTO implements Serializable {

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    /**
     * 用户头像访问路径
     */
    @NotBlank(message = "用户头像不能为空")
    private String src;

}
