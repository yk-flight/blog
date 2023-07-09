package com.zrkizzy.data.domain.monitor;

import lombok.Data;

import java.io.Serializable;

/**
 * 磁盘状态监控实体类
 *
 * @author zhangrongkang
 * @since 2023/7/7
 */
@Data
public class DiskMonitor implements Serializable {

    /**
     * 磁盘路径
     */
    private String diskPath;

    /**
     * 盘符类型
     */
    private String diskType;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 总大小
     */
    private String total;

    /**
     * 剩余大小
     */
    private String free;

    /**
     * 已经使用量
     */
    private String used;

    /**
     * 资源的使用率
     */
    private double usage;

}
