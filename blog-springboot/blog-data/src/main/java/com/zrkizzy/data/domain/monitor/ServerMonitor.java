package com.zrkizzy.data.domain.monitor;

import lombok.Data;

import java.io.Serializable;

/**
 * 服务器监控实体类
 *
 * @author zhangrongkang
 * @since 2023/7/7
 */
@Data
public class ServerMonitor implements Serializable {

    /**
     * 服务器名称
     */
    private String serverName;

    /**
     * 服务器IP
     */
    private String serverIp;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 系统架构
     */
    private String osArch;

    /**
     * 项目路径
     */
    private String projectPath;

}
