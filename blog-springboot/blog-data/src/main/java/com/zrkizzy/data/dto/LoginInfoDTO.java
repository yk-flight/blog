package com.zrkizzy.data.dto;

import com.zrkizzy.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 用户登录信息数据传输对象
 *
 * @author zhangrongkang
 * @since 2023/7/4
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class LoginInfoDTO extends BaseDTO {


    /**
     * 登录用户名称
     */
    private String username;

    /**
     * 登录IP
     */
    private String loginIp;

    /**
     * 登录位置
     */
    private String loginLocation;

    /**
     * 浏览器版本
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 登录状态：0 失败; 1 成功
     */
    private Boolean status;

    /**
     * 登录消息提示
     */
    private String message;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;

}
