package com.zrkizzy.data.vo.monitor;

import com.zrkizzy.data.domain.monitor.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * 服务监控数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/7/7
 */
@Data
public class ServiceMonitorVO implements Serializable {

    /**
     * CPU
     */
    private CpuMonitor cpuMonitor;

    /**
     * 内存
     */
    private MemoryMonitor memoryMonitor;

    /**
     * 服务器
     */
    private ServerMonitor serverMonitor;

    /**
     * JVM虚拟机
     */
    private JvmMonitor jvmMonitor;

    /**
     * 磁盘状态
     */
    private List<DiskMonitor> diskMonitors;

}
