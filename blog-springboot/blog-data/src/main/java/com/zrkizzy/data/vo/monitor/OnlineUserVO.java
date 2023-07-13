package com.zrkizzy.data.vo.monitor;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 在线用户数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/7/11
 */
@Data
public class OnlineUserVO implements Serializable {

    /**
     * 登录标识
     */
    private String track;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 登录IP
     */
    private String ipAddress;

    /**
     * IP属地
     */
    private String ipLocation;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 浏览器
     */
    private String browser;

}
