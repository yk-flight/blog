package com.zrkizzy.data.domain.monitor;

import com.zrkizzy.common.utils.CalculateUtil;
import com.zrkizzy.common.utils.TimeUtil;
import lombok.Setter;

import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.util.Date;

/**
 * Jvm虚拟机监控实体类
 *
 * @author zhangrongkang
 * @since 2023/7/7
 */
@Setter
public class JvmMonitor implements Serializable {
    
    /**
     * JVM占用的内存总大小
     */
    private double totalMemory;

    /**
     * JVM最大可用内存大小
     */
    private double maxAble;

    /**
     * JVM空闲内存
     */
    private double freeMemory;

    /**
     * JDK版本
     */
    private String version;

    /**
     * JDK路径
     */
    private String home;

    public double getTotalMemory() {
        return CalculateUtil.divide(totalMemory, (1024 * 1024), 2);
    }

    public double getMaxAble() {
        return CalculateUtil.divide(maxAble, (1024 * 1024), 2);
    }

    public double getFreeMemory() {
        return CalculateUtil.divide(freeMemory, (1024 * 1024), 2);
    }

    /**
     * JVM已用内存
     */
    public double getUsed() {
        return CalculateUtil.divide(totalMemory - freeMemory, (1024 * 1024), 2);
    }

    /**
     * JVM使用率
     */
    public double getUsage() {
        return CalculateUtil.multiply(CalculateUtil.divide(totalMemory - freeMemory, totalMemory, 4), 100);
    }

    /**
     * 获取JDK名称
     */
    public String getName() {
        return ManagementFactory.getRuntimeMXBean().getVmName();
    }

    public String getVersion() {
        return version;
    }

    public String getHome() {
        return home;
    }

    /**
     * JDK启动时间
     */
    public String getJdkStartTime() {
        // 获取JDK的启动时间
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        // 格式化JDK启动时间
        return TimeUtil.formatDate(new Date(time), TimeUtil.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * JDK运行时间
     */
    public String getJdkRunTime() {
        // 获取JDK的启动时间
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return TimeUtil.timeDistance(TimeUtil.now(), new Date(time));
    }

    /**
     * 运行参数
     */
    public String getInputArgs() {
        return ManagementFactory.getRuntimeMXBean().getInputArguments().toString();
    }

}
